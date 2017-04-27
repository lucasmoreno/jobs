package br.com.testepaggi.common;

import br.com.testepaggi.model.Cards;

/**
 * © Copyright 2017.
 * Autor : Paulo Sales - paulovitorns@gmail.com
 */

public interface CardsFinishedListener extends BaseCommonListener {

    void onCardsSuccess(Cards cards);

}
