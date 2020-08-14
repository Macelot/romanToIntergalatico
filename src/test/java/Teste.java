
import org.marcelot.Conversor;
import org.marcelot.Element;
import org.marcelot.Elemento;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class Teste {

    ArrayList<String> arquivo;
    HashMap<String,String> representa;
    ArrayList<Element> elements;
    String simbolos,romanos;
    int romanosConvertido;
    Conversor conversor;
    String[] tudo;
    String nomeMetal;
    Element el;

    @Test
    public void testaRomanos() {
        Conversor c = new Conversor();
        String[] ns;
        ns = new String[]{"I", "V", "X", "L", "C", "D", "M", "MCMXLIV", "MCMIII", "IL", "XLIX"};
        int resp;
        for (int i = 0; i < ns.length; i++) {
            resp = c.traduzirNumeralRomano(ns[i]);
            System.out.println(" -> " + resp);
        }
    }

    @Test
    public void testaArabicos() {
        Conversor c = new Conversor();
        int[] ns;
        ns = new int[]{1, 5, 10, 50, 100, 500, 1000, 1944, 1903, 49};
        String resp;
        for (int i = 0; i < ns.length; i++) {
            resp = c.traduzirNumeralArabico(ns[i]);
            System.out.println(" ---> " + resp);
        }
    }

    @Test
    public void testaRomanosPergunta() {
        conversor = new Conversor();
        String[] ns;
        //squid leij snob snob
        //snob krok
        //snob krok 36
        //36 = XXXVI
        ns = new String[]{"XLII","IV","IVXXXVI"};
        int resp;
        for (int i = 0; i < ns.length; i++) {
            resp = conversor.traduzirNumeralRomano(ns[i]);
            System.out.println(" -> " + resp);
        }
    }

    //representa
    @Test
    public void testaRepresenta(){
        this.arquivo = new ArrayList<String>();
        try {
            //String[] arquivio;
            //arquivio = new String[100];
            FileReader fr = new FileReader("entrada.txt");
            BufferedReader br = new BufferedReader(fr);
            String linha;
            linha = br.readLine();
            while(linha!=null){
                this.arquivo.add(linha);
                linha = br.readLine();
            }
        }catch (Exception e){

        }
        String key,valor;
        String[] partesRepresenta;
        representa = new HashMap<String,String>();
        for (int i=0; i<this.arquivo.size();i++) {
            if (this.arquivo.get(i).contains("representa")) {
                partesRepresenta = arquivo.get(i).split("representa");
                key = partesRepresenta[0];
                valor = partesRepresenta[1];
                representa.put(key.trim(), valor.replace(" ",""));
            }
        }
    }

    //ferro 195,50
    //IV ferro=782
    //valem
    @Test
    public void testaValem(){
        String[] partesValem,after,before;
        String allAfter,allBefore;
        String nome;
        conversor = new Conversor();
        int cred,creditosCalculados;


        elements = new ArrayList<Element>();

        for (int i=0; i<this.arquivo.size();i++) {
            if (this.arquivo.get(i).contains("valem")) {
                partesValem = arquivo.get(i).split("valem");
                allBefore=partesValem[0];
                allAfter=partesValem[1];
                before=allBefore.split(" ");
                after=allAfter.split(" ");
                //pegar o nome do metal
                nome=before[before.length-1];
                //descobrir quanto é cada before -1, pois o ultimo before é o metal
                //pegar o creditos da linha
                cred =Integer.parseInt(after[1]);
                romanos="";
                for (int j=0;j<before.length-1;j++){
                    simbolos=before[j];
                    romanos+=representa.get(simbolos);
                }
                romanosConvertido=conversor.traduzirNumeralRomano(romanos);
                creditosCalculados=cred/romanosConvertido;
                el = new Element(nome,creditosCalculados);
                elements.add(el);
            }
        }
    }

    //print representa
    @Test
    public void testaPrintRepresenta() {
        testaRepresenta();
        for (String keys : representa.keySet()) {
            System.out.println("{"+keys+"}");
            System.out.println("{"+representa.get(keys)+"}");
        }
    }

    //print elements
    @Test
    public void testaPrint() {
        testaRepresenta();
        testaValem();
        for(int i=0;i<elements.size();i++){
            System.out.println(elements.get(i).toString());
        }
    }

    //?
    @Test
    public void testaPerguntas(){
        testaRepresenta();
        testaValem();
        String[] afterVale;
        String simbolosConcatenados="";
        conversor=new Conversor();
        for (int i=0; i<this.arquivo.size();i++) {
            if (this.arquivo.get(i).contains("quanto ")) {
                tudo=this.arquivo.get(i).split("vale");
                afterVale=tudo[1].split(" ");
                //menos 1 posi nao precisa da interrogação
                romanos="";
                for (int j=1;j<afterVale.length-1;j++){
                    simbolos=(afterVale[j].replace(" ",""));//pegamos squid leij snob snob
                    simbolosConcatenados+=simbolos+" ";
                    romanos+=representa.get(simbolos);
                }
                romanosConvertido=conversor.traduzirNumeralRomano(romanos);
                if(romanosConvertido>0) {
                    System.out.println(simbolosConcatenados+ " vale " + romanosConvertido);
                }else{
                    System.out.println("Nem ideia do que isto significa!");
                }
            }
            if (this.arquivo.get(i).contains("quantos")) {
                tudo=this.arquivo.get(i).split("são");
                afterVale=tudo[1].split(" ");//after são
                //menos 2 pois temos o metal e a interrogação
                romanos="";
                simbolosConcatenados="";
                for (int j=1;j<afterVale.length-2;j++){// 1 para pular o primeiro espaço - 2 pois temos o metal e a interrogação
                    simbolos=(afterVale[j].replace(" ",""));//pegamos snob krok
                    simbolosConcatenados+=simbolos+" ";
                    romanos+=representa.get(simbolos);
                }
                nomeMetal = afterVale[afterVale.length-2];
                romanosConvertido=conversor.traduzirNumeralRomano(romanos);
                int valor = el.getElement(nomeMetal,elements).getCred();
                System.out.println(simbolosConcatenados + nomeMetal+ " custa " + (romanosConvertido*valor));
            }
        }
    }
}