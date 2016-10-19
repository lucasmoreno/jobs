defmodule Api.Card do
  use Api.Web, :model

  @primary_key {:id, Ecto.UUID, autogenerate: false}
  @foreign_key_type Ecto.UUID
  schema "cards" do
    has_many :payments, Api.Payment

    field :holder, :string
    field :number, :string
    field :cvv, :string
    field :expiration_month
    field :expiration_year

    timestamps()
  end

  @doc """
  Builds a changeset based on the `struct` and `params`.
  """
  def changeset(struct, params \\ %{}) do
    allowed_required = ~w(holder number cvv expiration_month expiration_year)a
    struct
    |> cast(params, allowed_required)
    |> validate_required(allowed_required)
    |> validate_length(:holder, max: 100)
    |> validate_length(:number, is: 16)
    |> validate_format(:number, ~r/^\d{16}$/)
    |> unique_constraint(:number)
    |> validate_length(:cvv, is: 3)
    |> validate_format(:cvv, ~r/^\d{3}$/)
    |> validate_length(:expiration_month, is: 3)
    |> validate_format(:expiration_month, ~r/^[a-zA-Z]{3}$/)
    |> validate_length(:expiration_year, is: 4)
    |> validate_format(:expiration_year, ~r/^[2-9]\d1[6-9]|[2-9]\d[2-9]\d$/)
  end

  def find_by_number(number) do
    query = from card in Api.Card,
      where: card.number == ^number,
      limit: 1,
      select: card

    Api.Repo.one query
  end
end
