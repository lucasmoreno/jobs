package br.com.testepaggi.model;


import br.com.testepaggi.R;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public enum ApiResponseType {
    PAGAMENTO_EMPTY(R.drawable.ic_payment_white_48dp, R.string.pagamento_empty, false),
    CARDS_EMPTY(R.drawable.ic_payment_white_48dp, R.string.cards_empty, false),
    TRANSACAO_EMPTY(R.drawable.ic_swap_vertical_circle_white_48dp, R.string.transacao_empty, false),
    SERVER_TIMEOUT(R.drawable.ic_access_time_white_48dp, R.string.server_timeout, true),
    SERVER_ERROR(R.drawable.ic_cloud_off_white_48dp, R.string.server_error, true),
    ERROR_INSERT_TRANSACAO(R.drawable.ic_swap_vertical_circle_white_48dp, R.string.nova_transacao_error, false),
    NO_INTENET_CONNECTION(R.drawable.ic_signal_wifi_off_white_48dp, R.string.no_connection, false);

    private int     iconResId;
    private int     strResId;
    private boolean tentarNovamente;

    /**
     * Construir um tipo o response do server ou erro
     * <p>
     *     Metodo usado para construir um novo objeto de erro ou resposta do servidor
     * </p>
     *
     * @param iconResId resource id do icone
     * @param strResId  resource id da string
     * @param tentarNovamente   se exibo o botão para tentar novamente.
     *
     */
    ApiResponseType(int iconResId, int strResId, boolean tentarNovamente){
        this.iconResId          = iconResId;
        this.strResId           = strResId;
        this.tentarNovamente    = tentarNovamente;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public int getStrResId() {
        return strResId;
    }

    public void setStrResId(int strResId) {
        this.strResId = strResId;
    }

    public boolean isTentarNovamente() {
        return tentarNovamente;
    }

    public void setTentarNovamente(boolean tentarNovamente) {
        this.tentarNovamente = tentarNovamente;
    }
}
