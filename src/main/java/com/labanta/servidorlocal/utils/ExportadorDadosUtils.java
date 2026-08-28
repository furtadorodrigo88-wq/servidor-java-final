package com.labanta.servidorlocal.utils;

import com.labanta.servidorlocal.model.PessoaModel;
import com.google.gson.Gson;

public class ExportadorDadosUtils {
    public void exportarCarrinhoParaJson(PessoaModel cliente) {

        Gson tradutor = new Gson();

        String json = tradutor.toJson(cliente.getServicosComprados());

        System.out.println(json);
    }
}
