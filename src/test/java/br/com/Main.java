package br.com;

import br.com.containner.GroupContainer;
import br.com.enums.Model;
import br.com.enums.Version;
import br.com.exceptions.ValidateException;
import br.com.groups.GroupRules;
import br.com.usage.model.Identificacao;
import br.com.usage.model.IdentificacaoWrapper;
import br.com.usage.model.Produto;
import br.com.evaluateables.Validateable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws Exception {

      /*  for (int i = 0; i <= 2; i++) {
            new Runner().start();
        }*/

        execute(getIdentificacao());
    }

    static void execute(Identificacao identificacao) throws ValidateException {

        for (GroupRules group : GroupContainer.getInstance().getGroups()) {
            Validateable validateable = new IdentificacaoWrapper(identificacao);
            group.execute(validateable);
        }
    }

    static Identificacao getIdentificacao() throws ParseException {

        Produto produto = new Produto();
        produto.setCst("");

        Identificacao identificacao = new Identificacao();
        identificacao.setDataEmissao(new SimpleDateFormat("yyyy/mm/dd").parse("2015/12/31"));

        identificacao.setIndIeDest('2');
        identificacao.setTipoAmbiente(1);
        identificacao.setProdutos(Arrays.asList(produto));
        identificacao.setCodigoRegimeTributario('2');
        identificacao.setModeloNFe(Model.MODELO_65);
        identificacao.setVersao(Version.V3_10);

        return identificacao;
    }
}

class Runner extends Thread {

    @Override
    public void run() {


        try {

            Thread.sleep(1 * 1000);

            Identificacao identificacao = Main.getIdentificacao();

            int i = 0;

            while (i<=1000000000) {

                try {

                    Main.execute(identificacao);
//                    Thread.sleep(1 * 1000);

                } catch (ValidateException e) {
                    System.out.println(e.getMensagemNFe());
                }

                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
