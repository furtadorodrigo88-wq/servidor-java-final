package com.labanta.servidorlocal.service;

import com.labanta.servidorlocal.exeption.ServiceNotFoundExeption;
import com.labanta.servidorlocal.model.ServiceModel;
import com.labanta.servidorlocal.repository.ServiceRrpository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {

    private final ServiceRrpository repositorio;

    public ServiceService(ServiceRrpository repositorio) {
        this.repositorio = repositorio;
    }

    public Page<ServiceModel> listarTodos(Pageable pageable) {
        return repositorio.findAll(pageable);
    }

    public ServiceModel criarService(ServiceModel novoService) {
        return repositorio.save(novoService);
    }

    public List<ServiceModel> aplicarDescontoEmAtivos(Double percentagem) {

        if (percentagem < 0 || percentagem > 100) {
            throw  new IllegalArgumentException ("Desconto invalido.");
        }

        List<ServiceModel> lista = repositorio.findByEstadoTrue();

        for (ServiceModel servico : lista) {
            double desconto = servico.getPreco() * percentagem / 100;
            double precoFinal = servico.getPreco() - desconto;

            servico.setPrecoComDesconto(precoFinal);
        }

        return repositorio.saveAll(lista);
    }

    public ServiceModel serviceById (long id){
        return repositorio.findById(id)
                .orElseThrow(() ->
                        new ServiceNotFoundExeption("o serviço com o id " + id + " nao existe no catalogo.")
                        );
    }

    public List<ServiceModel> pesquisar (String termo) {
        return repositorio.findByTituloContainingIgnoreCase(termo);
    }
}