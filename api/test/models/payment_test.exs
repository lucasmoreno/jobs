defmodule Api.PaymentTest do
  use Api.ModelCase

  alias Api.Payment

  @valid_attrs %{
    amount: 120.5,
    intermediaries: [
      %{
        description: "test",
        fee: 0.02,
        flat: 2
      }
    ],
    card: %{
      cvv: "111",
      expiration_month: "FEB",
      expiration_year: "2016",
      holder: "Name",
      number: "1111111111111111"
    }
  }
  @invalid_attrs %{
    amount: 120.5,
    intermediaries: [],
    card: %{}
  }

  test "changeset with valid attributes" do
    changeset = Payment.changeset(%Payment{}, @valid_attrs)
    assert changeset.valid?
  end

  test "changeset with invalid attributes" do
    changeset = Payment.changeset(%Payment{}, @invalid_attrs)
    refute changeset.valid?
  end
end
