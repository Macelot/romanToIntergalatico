package org.marcelot;

import java.util.ArrayList;

public class Element {
    private String n;
    private String n2;
    private int cred;

    public Element() {

    }

    public Element(String n) {
        this.n=n;
        this.setN2(n);
    }

    public Element(String n, int c) {
        this.n=n;
        this.setN2(n);
        this.cred=c;
    }



    public void setN2(String n) {
        if(n.equals("Prata")){
            this.n2 = "Silver";
        }
        if(n.equals("Ouro")){
            this.n2 = "Gold";
        }
        if(n.equals("Ferro")){
            this.n2 = "Iron";
        }
        if(n.equals("Silver")){
            this.n2 = "Prata";
        }
        if(n.equals("Gold")){
            this.n2 = "Ouro";
        }
        if(n.equals("Iron")){
            this.n2 = "Ferro";
        }

    }


    public String getN2() {
        return n2;
    }

    public int getCred() {
        return cred;
    }

    public void setCred(int cred) {
        this.cred = cred;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public Element getElement(String chave, ArrayList<Element> el){
        Element e=new Element();
        for (int i=0;i<el.size();i++){
            if(chave.equals(el.get(i).getN())){
                e=el.get(i);
            }
            if(chave.equals(el.get(i).getN2())){
                e=el.get(i);
            }
        }
        return e;

    }

    @Override
    public String toString(){
        String s="";
        s+=this.getN();
        s+=" ("+this.getN2()+") ";
        s+=" "+this.getCred();
        return s;

    }

}
