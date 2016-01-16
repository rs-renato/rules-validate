package br.com.rules;

import br.com.rules.containner.GroupContainner;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.enums.Version;
import br.com.rules.exceptions.RuleException;
import br.com.rules.group.RuleGroup;
import br.com.rules.model.Identificacao;
import br.com.rules.model.Produto;
import br.com.rules.wrapper.IdentificacaoWrapper;
import br.com.rules.wrapper.Validateable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;


public class Main {

    public static void main(String[] args) throws Exception {

       /* for (int i = 0; i <= 300; i++) {
            new Runner().start();
        }*/

        execute(getIdentificacao());
    }

    static void execute(Identificacao identificacao) throws RuleException {

        for (RuleGroup group : GroupContainner.getInstance().getGroups()) {
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
        identificacao.setModeloNFe(ModeloNFe.MODELO_55);
        identificacao.setVersao(Version.V3_10);

        return identificacao;
    }
}

class Runner extends Thread {

    @Override
    public void run() {


        try {

            Thread.sleep(10 * 1000);

            Identificacao identificacao = Main.getIdentificacao();

            int i = 0;

            while (i<=1000000000) {

                try {

                    Main.execute(identificacao);
                    Thread.sleep(1 * 1000);

                } catch (RuleException e) {
                    System.out.println(e.getMensagemNFe());
                }

                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
