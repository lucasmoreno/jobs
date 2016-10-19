defmodule Api.Repo.Migrations.CreateCard do
  use Ecto.Migration

  def change do
    create table(:cards, primary_key: false) do
      add :id, :uuid, default: fragment("uuid_generate_v4()"), primary_key: true
      add :holder, :string, size: 100, null: false
      add :number, :string, size: 16, null: false
      add :cvv, :string, size: 3, null: false
      add :expiration_month, :string, size: 3, null: false
      add :expiration_year, :string, size: 4, null: false

      timestamps()
    end

    create unique_index(:cards, :number)
  end
end
