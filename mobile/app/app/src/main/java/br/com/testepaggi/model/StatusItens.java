package br.com.testepaggi.model;


import br.com.testepaggi.R;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public enum StatusItens {

    DECLINED("", R.drawable.ic_close_white_24dp, R.color.red),
    APPROVED("", R.drawable.ic_check_white_24dp, R.color.green),
    NOT_CLEARED("", R.drawable.ic_info_outline_white_24dp, R.color.yellow),
    CANCELLED("", R.drawable.ic_close_white_24dp, R.color.red),
    COMPESATED("", R.drawable.ic_check_white_24dp, R.color.green),
    PENDING("", R.drawable.ic_info_outline_white_24dp, R.color.yellow),
    DEFAULT("", R.drawable.ic_info_outline_white_24dp, R.color.yellow);

    private String  status;
    private int     iconResource;
    private int     colorIcon;

    /**
     * Construir um tipo o response do server ou erro
     * <p>
     *     Metodo usado para construir um novo objeto de erro ou resposta do servidor
     * </p>
     *
     * @param status        status do item
     * @param iconResource  resource id do icone do status
     * @param colorIcon     resource id da cor do icone
     *
     */
    StatusItens(String status, int iconResource, int colorIcon){
        this.status         = status;
        this.iconResource   = iconResource;
        this.colorIcon      = colorIcon;
    }

    /**
     * Verifico qual é o tipo de status
     * <p>
     *     Metodo usado para resgatar o tipo de status a ser recuperado
     * </p>
     *
     * @param   status      status recuperado do servidor
     * @return  StatusItens tipo do item recuperado
     * @see     StatusItens
     */
    public static StatusItens getItemStatus(String status){

        StatusItens statusItens;

        switch (status){
            case "declined":
                statusItens = StatusItens.DECLINED;
                break;
            case "approved":
                statusItens = StatusItens.APPROVED;
                break;
            case "not_cleared":
                statusItens = StatusItens.NOT_CLEARED;
                break;
            case "cancelled":
                statusItens = StatusItens.CANCELLED;
                break;
            case "compensated":
                statusItens = StatusItens.COMPESATED;
                break;
            case "pending":
                statusItens = StatusItens.PENDING;
                break;
            default:
                statusItens = StatusItens.DEFAULT;
                break;
        }

        status = status.replace("_", " ");
        statusItens.setStatus(status);
        return statusItens;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }

    public int getColorIcon() {
        return colorIcon;
    }

    public void setColorIcon(int colorIcon) {
        this.colorIcon = colorIcon;
    }

}
