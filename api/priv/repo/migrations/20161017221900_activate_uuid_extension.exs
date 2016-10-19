defmodule Api.Repo.Migrations.ActivateUuidExtension do
  use Ecto.Migration

  def up do
    execute "create extension if not exists \"uuid-ossp\";"
  end

  def down do
    execute "drop extension if exists \"uuid-ossp\";"
  end
end
