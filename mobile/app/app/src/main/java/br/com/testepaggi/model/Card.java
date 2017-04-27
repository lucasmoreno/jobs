package br.com.testepaggi.model;

import java.io.Serializable;

import br.com.testepaggi.business.api.vo.response.CardsItemResponseVO;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class Card implements Serializable{

    private static final String KEY = Card.class.getSimpleName();
    private String cardId;

    public Card(CardsItemResponseVO responseVO) {
        this.cardId = responseVO.cardId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
