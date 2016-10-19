defmodule Api.Repo.Migrations.CreatePayment do
  use Ecto.Migration

  def change do
    create table(:payments, primary_key: false) do
      add :id, :uuid, default: fragment("uuid_generate_v4()"), primary_key: true
      add :amount, :decimal, precision: 2, null: false
      add :intermediaries, :jsonb
      add :card_id, references(:cards, type: :uuid, on_delete: :nothing), null: :false

      timestamps()
    end

    create index(:payments, :card_id)
  end
end
