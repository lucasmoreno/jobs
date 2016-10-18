defmodule Api.Payment do
  use Api.Web, :model

  @primary_key {:id, Ecto.UUID, autogenerate: false}
  @foreign_key_type Ecto.UUID
  schema "payments" do
    belongs_to :card, Api.Card

    field :amount, :decimal
    field :intermediaries, {:array, :map}, default: []

    timestamps()
  end

  @doc """
  Builds a changeset based on the `struct` and `params`.
  """
  def changeset(struct, params \\ %{}) do
    struct
    |> cast(params, [:amount, :intermediaries])
    |> validate_required([:amount])
    |> cast_assoc(:card, required: true)
  end
end
