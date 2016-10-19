defmodule Api.Payment do
  use Api.Web, :model

  @primary_key {:id, Ecto.UUID, autogenerate: false}
  @foreign_key_type Ecto.UUID
  schema "payments" do
    belongs_to :card, Api.Card

    field :amount, :decimal

    embeds_many :intermediaries, Api.Intermediary

    timestamps()
  end

  @doc """
  Builds a changeset based on the `struct` and `params`.
  """
  def changeset(struct, params \\ %{}) do
    intermediaries =
      params["intermediaries"]
      |> Enum.map(
    fn intermediary ->
      Api.Intermediary.changeset(%Api.Intermediary{}, params["amount"], intermediary)
    end)


    struct
    |> cast(params, [:amount])
    |> validate_required([:amount])
    |> put_assoc(:card, params["card"])
    |> put_embed(:intermediaries, intermediaries)
  end
end
