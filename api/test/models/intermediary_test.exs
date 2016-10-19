defmodule Api.IntermediaryTest do
  use Api.ModelCase

  alias Api.Intermediary

  @valid_attrs %{
    description: "test",
    fee: 0.02,
    flat: 2
  }
  @invalid_attrs %{
    description: 5,
    fee: "0.02",
    flat: "2"
  }

  test "changeset with valid attributes" do
    changeset = Intermediary.changeset(%Intermediary{}, 5, @valid_attrs)
    assert changeset.valid?
  end

  test "changeset with invalid attributes" do
    changeset = Intermediary.changeset(%Intermediary{}, 5, @invalid_attrs)
    refute changeset.valid?
  end

  test "changeset with correct amount" do
    changeset = Intermediary.changeset(%Intermediary{}, 5, @valid_attrs)
    assert changeset.changes[:amount] == 2.1
  end
end
