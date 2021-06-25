package soares.matheus.appdevelopment;

import java.io.Serializable;

public class Dev implements Serializable {

    public int id;
    public String nome,email,descr,development;

    public Dev() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Dev(String nome, String development){
        this.nome = nome;
        this.setDevelopment(development);
    }

    public Dev(int id,String nome, String email,String development,String descr){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.setDevelopment(development);
        this.descr = descr;
    }

    public String getDevelopment() {

        return development;
    }
    public int setDevelopment(String development) {
        this.development = development;
        return 0;
    }
    ///////////////////////////////////////////////////////////////////////////


    @Override
    public String toString() {
        return id + " _ " + nome + " | " + development;
    }
}
