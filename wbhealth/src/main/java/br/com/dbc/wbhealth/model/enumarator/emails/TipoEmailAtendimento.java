package br.com.dbc.wbhealth.model.enumarator.emails;

public enum TipoEmailAtendimento {

    CONFIRMACAO(0, "Confirmação de atendimento!"), ATUALIZACAO(1, "Alteração no atendimento realizada!"), CANCELAMENTO(2, "");

    private int codigo;

    private String titulo;

    TipoEmailAtendimento(int codigo, String titulo){
        this.codigo = codigo;
        this.titulo = titulo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public static TipoEmailAtendimento valueOf(int code){
        for (TipoEmailAtendimento valor: TipoEmailAtendimento.values()) {
            if(valor.getCodigo() == code){
                return valor;
            }
        }
        throw new IllegalArgumentException("Código inválido");
    }

}
