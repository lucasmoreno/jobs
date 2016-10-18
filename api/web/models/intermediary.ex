defmodule Api.Intermediary do
  use Api.Web, :model

  embedded_schema do
    field :fee, :float
    field :flat, :float
    field :description, :string
    field :amount, :float
  end

  def changeset(struct, payment_amount, params \\ %{}) do
    allowed = ~w(fee flat description amount)a
    required = ~w(fee flat description)a

    struct
    |> cast(params, allowed)
    |> validate_required(required)
    |> put_change(:amount, calculate_amount(payment_amount, params))
  end

  defp calculate_amount(payment_amount, params) when is_map(params) do
    {result, _string} =
      payment_amount
      |> Decimal.new
      |> Decimal.mult(Decimal.new(params[:fee]))
      |> Decimal.add(Decimal.new(params[:flat]))
      |> Decimal.to_string(:normal)
      |> Float.parse

    result
  end
end
