defmodule Api.ChargeController do
  use Api.Web, :controller

  def create(conn, params) do
    card =
      with nil <- Api.Card.find_by_number(params["card"]["number"]),
           changeset <- Api.Card.changeset(%Api.Card{},params["card"]),
           {:ok, card} <- Repo.insert(changeset) do
        card
      else
        {:error, changeset} ->
          changeset
        card ->
          card
      end

    params = Map.put(params, "card", card)
    changeset = Api.Payment.changeset(%Api.Payment{}, params)

    case Repo.insert(changeset) do
      {:ok, result} ->
        render(conn, Api.ChargeView, :charge, created: result)
          {:error, changeset} ->
        render(conn, Api.ChargeView, :charge, error: changeset)
    end
  end
end
