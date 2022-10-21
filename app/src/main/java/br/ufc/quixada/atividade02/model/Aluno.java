package br.ufc.quixada.atividade02.model;

public class Aluno {

    static int ids = 0;

    private int id;
    private String nome;
    private String matricula;
    private String curso;

    public Aluno(){}

    public Aluno(String nome, String matricula, String curso) {
        this.id = ids++;

        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Aluno: " + nome + " - " + matricula + " - " + curso;
    }
}
