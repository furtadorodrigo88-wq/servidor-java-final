package com.labanta.servidorlocal.controller;

import com.labanta.servidorlocal.model.ServiceModel;
import com.labanta.servidorlocal.service.EmailService;
import com.labanta.servidorlocal.service.ExchangeService;
import com.labanta.servidorlocal.service.FileStorageService;
import com.labanta.servidorlocal.service.ServiceService;
import com.labanta.servidorlocal.dto.ServiceResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/servicos")
public class ServiceController {

    @GetMapping("/teste")
    public String dizerOla(){
        return  "ola mundo! o meu servidor spring esta a funcionar";
    }

    private final ExchangeService exchangeService;
    private final ServiceService service;
    private final EmailService emailService;
    private final FileStorageService fileStorageService;

    public ServiceController(ServiceService service, ExchangeService exchangeService, EmailService emailService, FileStorageService fileStorageService) {
        this.service = service;
        this.exchangeService = exchangeService;
        this.emailService = emailService;
        this.fileStorageService = fileStorageService;
    }
    @Operation(
            summary = "Listar todos os serviços",
            description = "Rota para listar todos os servisso da plataforma"
    )
    @GetMapping
    public Page<ServiceModel> serviceAll(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.listarTodos(pageable);
    }

    @Operation(
            summary = "Criar um novo serviço",
            description = "Rota para criar um novo serviço"
    )
    @SecurityRequirement(name = "BearerAuth")
    @PostMapping
    public ServiceModel createService(@RequestBody ServiceModel novoService) {
        return service.criarService(novoService);
    }

    @Operation(
            summary = "Aplicar desconto no serviço",
            description = "Aplicar desconto em todos os serviços da plataforma"
    )
    @SecurityRequirement(name = "BearerAuth")
    @PostMapping("/aplicar-desconto")
    public List<ServiceResponseDTO> applyDiscount(@RequestBody Double percentagem) {

        List<ServiceModel> lista = service.aplicarDescontoEmAtivos(percentagem);

        List<ServiceResponseDTO> resposta = new ArrayList<>();

        for (ServiceModel servico : lista) {
            ServiceResponseDTO dto = new ServiceResponseDTO(
                    servico.getTitulo(),
                    servico.getPrecoComDesconto()
            );

            resposta.add(dto);
        }

        return resposta;
    }

    @Operation(
            summary = "Buscar serviços por id",
            description = "Pesquisa por um serviço atraves do seu id"
    )
    @GetMapping("/{id}")
    public ServiceModel serviceById(@PathVariable long id){
        return service.serviceById(id);
    }

    @Operation(
            summary = "Procura por termo",
            description = "Faz uma pesquisa por todos os serviços que tenha o mesmo termo indecado"
    )
    @GetMapping("/pesquisa")
    public List<ServiceModel> research (@RequestParam String termo){
        return service.pesquisar(termo);
    }

    @Operation(
            summary = "Pedir orçamento de um serviço",
            description = "Faz um pedido de orçamento,comverte para a moeda indicada e emvia o orçamento por email"
    )
    @SecurityRequirement(name = "BearerAuth")
    @PostMapping("/{id}/orcamento")
    public String budgetAsk (@PathVariable Long id, @RequestParam String emailDestino,@RequestParam (defaultValue = "CVE") String moeda){
        ServiceModel servico = service.serviceById(id);
        double precoConvertido = exchangeService.converterPreco(servico.getPreco(), moeda);
        emailService.enviarOrcamentoPorEmail(emailDestino, servico.getTitulo(), precoConvertido, moeda);
        return "orçamento calculado e enviado com sucesso para " + emailDestino + "!";
    }

    @Operation(
            summary = "Upload de imagens",
            description = "Faz o upload de imagens da capa de serviço"
    )
    @SecurityRequirement(name = "BearerAuth")
    @PostMapping(value = "/{id}/upload-capa", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile (@RequestParam("file") MultipartFile file, @PathVariable Long id) {
        ServiceModel servico = service.serviceById(id);
        String fileUpload = fileStorageService.storeImage(file);
        servico.setImagenCapa(fileUpload);
        service.criarService(servico);
        return ResponseEntity.ok("Imagen caregado com sucesso: " + fileUpload);
    }
}


