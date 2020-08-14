package org.marcelot;

import java.util.ArrayList;

public class Elemento {
    private String nome;
    private String nomeInternacional;
    private int creditos;
    private int calculo;
    private ArrayList<String> simbolos;

    public Elemento() {

    }

    public Elemento(String n) {
        this.nome=n;
        this.setNomeInternacional(n);
    }


    public Elemento getElemento(String chave, ArrayList<Elemento> el){
        Elemento e=new Elemento();
        for (int i=0;i<el.size();i++){
            if(chave.equals(el.get(i).getNome())){
                e=el.get(i);
            }
            if(chave.equals(el.get(i).getNomeInternacional())){
                e=el.get(i);
            }
        }
        return e;

    }


    public Elemento(String n,int c, ArrayList<String> s) {
        this.creditos=c;
        this.nome=n;
        this.simbolos=s;
        this.setNomeInternacional(n);
    }

    public String getNomeInternacional() {
        return nomeInternacional;
    }

    public void setCalculo(int simbs, int c){
        int result=0;
        //result = c * simbs; //isso eu demorei para entender!!!!
        //result = c + simbs; //isso eu demorei para entender!!!!
//        System.out.println(c);
//        System.out.println(simbs);
//        if(c>simbs)
//            result=c+simbs;
//        if(simbs>c)
//            result=simbs-c;
//        this.calculo=result;
        this.calculo=c/simbs;

    }

    public void setNomeInternacional(String c) {
        if(nome.equals("Prata")){
            this.nomeInternacional = "Silver";
        }
        if(nome.equals("Ouro")){
            this.nomeInternacional = "Gold";
        }
        if(nome.equals("Ferro")){
            this.nomeInternacional = "Iron";
        }

        if(nome.equals("Silver")){
            this.nomeInternacional = "Prata";
        }
        if(nome.equals("Gold")){
            this.nomeInternacional = "Ouro";
        }
        if(nome.equals("Iron")){
            this.nomeInternacional = "Ferro";
        }

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getCalculo() {
        return calculo;
    }

    public void setCalculo(int calculo) {
        this.calculo = calculo;
    }

    public ArrayList<String> getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(ArrayList<String> simbolos) {
        this.simbolos = simbolos;
    }

    public String getSimbolosString() {
        String s="";
        for (String t:this.getSimbolos()) {
            s+=t+" ";
        }
        return s;
    }

    @Override
    public String toString(){
        String s="";
        s+=this.getNome();
        s+=" ("+this.getNomeInternacional()+") ";
        s+=" "+this.getCreditos();
        s+=" "+this.getSimbolosString();
        s+=" "+this.getCalculo();

        return s;

    }
}
