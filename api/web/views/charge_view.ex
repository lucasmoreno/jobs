defmodule Api.ChargeView do
  use Api.Web, :view
  
  defp translate_errors(changeset) do
    Ecto.Changeset.traverse_errors(changeset, &translate_error/1)
  end

  def render("charge.json", %{created: payment}) do
    %{
      id: payment.id,
      amount: payment.amount,
      inserted_at: payment.inserted_at,
      updated_at: payment.updated_at,
      card_id: payment.card_id,
      intermediaries: Enum.map(payment.intermediaries,&Map.from_struct/1)
    }
  end

  def render("charge.json", %{error: params}) do
    %{errors: translate_errors(params)}
  end
end
