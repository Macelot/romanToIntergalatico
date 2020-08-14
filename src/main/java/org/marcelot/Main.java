package org.marcelot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        ArrayList<String> arquivo;
        arquivo = new ArrayList<String>();
        Elemento eee;
        eee=new Elemento();

        try {
            //String[] arquivio;
            //arquivio = new String[100];
            FileReader fr = new FileReader("entrada.txt");
            BufferedReader br = new BufferedReader(fr);
            String linha;
            linha = br.readLine();
            while(linha!=null){
                //System.out.println(linha);
                arquivo.add(linha);
                linha = br.readLine();
            }
        }catch (Exception e){

        }
        System.out.println("Conteudo do arquivo");
        for (int i=0; i< arquivo.size();i++){
            System.out.println(arquivo.get(i));
        }

        System.out.println("------------------------Simbolos");
        String[] linhaCompleta;

        Map<String,String> codigos = new HashMap<String,String>();

        ArrayList<Elemento> elementos = new ArrayList<>();

        for (int i=0; i< arquivo.size();i++){
            linhaCompleta=arquivo.get(i).split(" ");
            //System.out.print("LINHA "+i + " ");
            for(int j=0;j< linhaCompleta.length;j++){
                //System.out.print(" parte "+j+" "+linhaCompleta[j]);
                if(linhaCompleta[j].equals("representa")) {
                    System.out.println(linhaCompleta[0]);
                    codigos.put(linhaCompleta[0], linhaCompleta[2]);
                    //codigos.put(linhaCompleta[2], linhaCompleta[0]);
                }
                if(linhaCompleta[j].equals("valem")) {
                    String[] linhaValem=linhaCompleta;
                    ArrayList dadosAntesDoVale = new ArrayList<String>();
                    String dadosDepoisValem="";
                    for (int k=0;k<linhaValem.length;k++){
                        if(!linhaValem[k].equals("valem")){
                            dadosAntesDoVale.add(linhaValem[k]);
                        }else{
                            dadosDepoisValem=linhaValem[k+1];
                            break;
                        }
                    }

                    for (int l=0;l<dadosAntesDoVale.size();l++){
                        System.out.print("--> "+dadosAntesDoVale.get(l));
                    }
                    System.out.println("=="+dadosDepoisValem);

                    Elemento e;
                    //nome, creditos e simbolo
                    String nome = (String) dadosAntesDoVale.get(dadosAntesDoVale.size()-1);
                    int creditos = Integer.valueOf(dadosDepoisValem);
                    //remover o metal dos simbolos
                    dadosAntesDoVale.remove(dadosAntesDoVale.size()-1);
                    e = new Elemento(nome,creditos,dadosAntesDoVale);
                    elementos.add(e);
                }
            }
            //System.out.println(" ");
        }

        System.out.println("-----------------------Elementos identificados");
        for (int i=0;i<elementos.size();i++){
            System.out.println(elementos.get(i).toString());
        }


        //System.out.println("-----------------------descobrir valor \"calculo\" dos elementos");
        //descobrir valor "calculo" dos elementos
        Conversor conversor;


        Map<String,Elemento> elementosComValor = new HashMap<String,Elemento>();

        for (int i=0;i<elementos.size();i++){
            conversor = new Conversor();
            Elemento e;
            e = elementos.get(i);
            //System.out.printf(" "+e.getSimbolosString());
            int t=0;
            for (int simb=0;simb<e.getSimbolos().size();simb++){
                String valor = e.getSimbolos().get(simb);
                //System.out.println("valor "+codigos.get(valor));
                //String temp = new StringBuilder(codigos.get(valor)).reverse().toString();
                //t+=conversor.traduzirNumeralRomano(new StringBuilder(codigos.get(valor)).reverse().toString());
                t+=conversor.traduzirNumeralRomano(codigos.get(valor));
            }

            e.setCalculo(t,e.getCreditos());
            elementosComValor.put(e.getNome(),e);//aqui temos os elementos com chave de nome
            //System.out.println(e.getCalculo());
            //System.out.println(" >> "+t);
        }

        System.out.println("-----------------------Elementos identificados NOVAMENTE");
        for (int i=0;i<elementos.size();i++){
            System.out.println(elementos.get(i).toString());
        }

        System.out.println("-----------------------??????????????????????????");
        //quanto vale
        for (int i=0; i< arquivo.size();i++){//passamos pelo arquivo
            linhaCompleta=arquivo.get(i).split(" ");
            int soma=0;

            conversor = new Conversor();
            if(linhaCompleta[linhaCompleta.length-1].equals("?")){//se tiver ?
                String rom="";
                for(int j=0;j< linhaCompleta.length;j++){
                    if(codigos.containsKey(linhaCompleta[j])){//se tiver simbolo
                        String valor = linhaCompleta[j];
                        System.out.print(valor+" ");
                        //soma+=conversor.traduzirNumeralRomano(codigos.get(valor));
                        rom+=codigos.get(valor);
                    }
                }
                soma=conversor.traduzirNumeralRomano(rom);
                if(arquivo.get(i).contains("vale")){
                    if(soma==0){
                        System.out.print("Nem ideia do que isto significa! ");
                    }else{
                        System.out.print("vale "+soma);
                    }

                }

                if(arquivo.get(i).contains("são")){
                    Elemento ee = new Elemento(linhaCompleta[linhaCompleta.length-2]);
                    System.out.print(ee.getNome());
                    String[] sibsLinha=arquivo.get(i).split(" ");

                    String romanoPergunta="";
                    for (int pos=0;pos<sibsLinha.length;pos++){
                        if(codigos.containsKey(sibsLinha[pos])){
                            //System.out.println("s= "+sibsLinha[pos]);
                            romanoPergunta+=codigos.get(sibsLinha[pos]);
                           /// System.out.println("rp "+romanoPergunta);
                        }
                    }
                    //System.out.print(romanoPergunta+" "+ee.getNome());
                    //System.out.print(romanoPergunta+" "+elementosComValor.get(ee.getNome()));
                    //System.out.print(romanoPergunta+" "+eee.getElemento(ee.getNome(),elementos).getCreditos());
                    //romanoPergunta=new StringBuilder(romanoPergunta).reverse().toString();
                    int s = conversor.traduzirNumeralRomano(romanoPergunta);
                    //int e = +eee.getElemento(ee.getNome(),elementos).getCreditos();
                    int e = +eee.getElemento(ee.getNome(),elementos).getCalculo();

                    System.out.print(" "+(s*e));

                }

                System.out.println();
            }
        }


        //Criação do HashMap
        System.out.println("------------------------Valores");//já vem ordenado alfabético pela chave
        for(String chave: codigos.keySet()) {
            String valor = codigos.get(chave);
            System.out.println(chave + " " + valor);
        }

        Conversor c;
        c = new Conversor();

        //Criação do HashMap simbolosCredito
        System.out.println("------------------------simbolosCredito");
        Map<String,Integer> simbolosCredito = new HashMap<String,Integer>();
        for(String chave: codigos.keySet()) {
            String valor = codigos.get(chave);
            simbolosCredito.put(chave,c.traduzirNumeralRomano(valor));
            System.out.println(chave + " " + valor + " " + c.traduzirNumeralRomano(valor));
        }

        //Elemento
        Map<String,String> Arabicos = new HashMap<String,String>();



    }
}
