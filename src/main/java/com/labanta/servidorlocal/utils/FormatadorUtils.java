package com.labanta.servidorlocal.utils;

import com.labanta.servidorlocal.model.DigitalServiceModel;
import com.labanta.servidorlocal.model.PessoaModel;
import com.labanta.servidorlocal.model.ServiceModel;
import com.labanta.servidorlocal.model.VendedorModel;

public class FormatadorUtils {
    public void imprimirPerfilPessoa(PessoaModel cliente) {
        System.out.println("=================================");
        System.out.println("👤 PERFIL DE UTILIZADOR");
        System.out.println("=================================");
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Morada: " + cliente.getMorada());
        System.out.println("=================================\n");
    }

    public void imprimirVendedor(VendedorModel vendedor) {
        System.out.println("=================================");
        System.out.println("👤 PERFIL DE Vendedor");
        System.out.println("=================================");
        System.out.println("Nome: " + vendedor.getNome());
        System.out.println("Morada: " + vendedor.getMorada());
        System.out.println("Morada: " + vendedor.getTaxaComissao());
        System.out.println("=================================\n");
    }

    public void imprimirDetalheServico(ServiceModel servico) {
        System.out.println("📦 SERVIÇO: " + servico.getTitulo());
        System.out.println("Preço: " + servico.getPreco() + " CVE");
        System.out.println("Estado Ativo: " + servico.isEstado() + "\n");
    }

    public void imprimirDetalheServicoDig(DigitalServiceModel servico) {
        System.out.println("📦 SERVIÇO: " + servico.getTitulo());
        System.out.println("Preço: " + servico.getPreco() + " CVE");
        System.out.println("Estado Ativo: " + servico.isEstado() + "\n");
        System.out.println("link de danlowd: " + servico.getLinkDownload() );
    }

    public void imprimirListaDeServicosComprados(PessoaModel pessoa) {
        System.out.println("\n===== SERVIÇOS COMPRADOS =====");

        for (ServiceModel servico : pessoa.getServicosComprados()) {
            System.out.println("Nome: " + servico.getTitulo());
            System.out.println("Descrição: " + servico.getDescricao());
            System.out.println("Preço: " + servico.getPreco() + " CVE");
            System.out.println("----------------------------");
        }
    }
}
