package br.com.testepaggi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.testepaggi.business.api.vo.response.CardsItemResponseVO;
import br.com.testepaggi.business.api.vo.response.CardsResponseVO;
import br.com.testepaggi.business.api.vo.response.PagamentoItemResponseVO;
import br.com.testepaggi.business.api.vo.response.PagamentoResponseVO;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public class Cards implements Serializable {

    private static final String KEY = Cards.class.getSimpleName();
    private int total;
    private List<Card> cards;
    private Random randomGenerator;

    public Cards(CardsResponseVO responseVo) {
        this.total      = responseVo.total;
        this.cards      = new ArrayList<>();
        randomGenerator = new Random();
        geraCards(responseVo);
    }

    private void geraCards(CardsResponseVO responseVO){
        for (CardsItemResponseVO response : responseVO.itens){
            this.cards.add(new Card(response));
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Card getRadonCard(){

        int index = randomGenerator.nextInt(cards.size());
        return checkIfIsNull(cards.get(index));
    }

    private Card checkIfIsNull(Card card){
        if(card.getCardId() == null)
            return getRadonCard();
        else
            return card;
    }
}
