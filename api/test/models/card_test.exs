defmodule Api.CardTest do
  use Api.ModelCase

  alias Api.Card

  @valid_attrs %{
    cvv: "111",
    expiration_month: "FEB",
    expiration_year: "2016",
    holder: "Name",
    number: "1111111111111111"
  }
  @invalid_attrs %{
    cvv: "11",
    expiration_month: "FE",
    expiration_year: "201",
    holder: "Name",
    number: "1111111111"
  }


  test "changeset with valid attributes" do
    changeset = Card.changeset(%Card{}, @valid_attrs)
    assert changeset.valid?
  end

  test "changeset with invalid attributes" do
    changeset = Card.changeset(%Card{}, @invalid_attrs)
    refute changeset.valid?
  end

  # cvv field
  test "changeset with missing cvv" do
    attrs = Map.delete(@valid_attrs, :cvv)

    changeset = Card.changeset(%Card{}, attrs)
    assert changeset.errors == [cvv: {"can't be blank",[]}]
  end

  test "changeset with cvv invalid length" do
    attrs = Map.put(@valid_attrs, :cvv, "1234")

    changeset = Card.changeset(%Card{}, attrs)
    assert changeset.errors == [cvv: {"has invalid format", []},
                                cvv: {"should be %{count} character(s)", [count: 3]}]
  end

  test "changeset with cvv invalid format" do
    attrs = Map.put(@valid_attrs, :cvv, "abc")

    changeset = Card.changeset(%Card{}, attrs)
    assert changeset.errors == [cvv: {"has invalid format", []}]
  end

  # expiration_month field
  test "changeset with missing expiration_month" do
    attrs = Map.delete(@valid_attrs, :expiration_month)

    changeset = Card.changeset(%Card{}, attrs)
    assert changeset.errors == [expiration_month: {"can't be blank",[]}]
  end

  test "changeset with expiration_month invalid length" do
    attrs = Map.put(@valid_attrs, :expiration_month, "february")

    changeset = Card.changeset(%Card{}, attrs)
    assert changeset.errors == [expiration_month: {"has invalid format", []},
                                expiration_month: {"should be %{count} character(s)", [count: 3]}]
  end

  test "changeset with expiration_month invalid format" do
    attrs = Map.put(@valid_attrs, :expiration_month, "123")

    changeset = Card.changeset(%Card{}, attrs)
    assert changeset.errors == [expiration_month: {"has invalid format", []}]
  end

  # expiration_year field
  test "changeset with missing expiration_year" do
    attrs = Map.delete(@valid_attrs, :expiration_year)

    changeset = Card.changeset(%Card{}, attrs)
    assert changeset.errors == [expiration_year: {"can't be blank",[]}]
  end

  test "changeset with expiration_year invalid length" do
    attrs = Map.put(@valid_attrs, :expiration_year, "201")

    changeset = Card.changeset(%Card{}, attrs)
    assert changeset.errors == [expiration_year: {"has invalid format", []},
                                expiration_year: {"should be %{count} character(s)", [count: 4]}]
  end

  test "changeset with expiration_year invalid format" do
    attrs = Map.put(@valid_attrs, :expiration_year, "2010")

    changeset = Card.changeset(%Card{}, attrs)
    assert changeset.errors == [expiration_year: {"has invalid format", []}]
  end

  # number field
  test "changeset with missing number" do
    attrs = Map.delete(@valid_attrs, :number)

    changeset = Card.changeset(%Card{}, attrs)
    assert changeset.errors == [number: {"can't be blank",[]}]
  end

  test "changeset with number invalid length" do
    attrs = Map.put(@valid_attrs, :number, "1111 1111 1111 1111")

    changeset = Card.changeset(%Card{}, attrs)
    assert changeset.errors == [number: {"has invalid format", []},
                                number: {"should be %{count} character(s)", [count: 16]}]
  end

  test "changeset with number invalid format" do
    attrs = Map.put(@valid_attrs, :number, "abcd1111abcd1111")

    changeset = Card.changeset(%Card{}, attrs)
    assert changeset.errors == [number: {"has invalid format", []}]
  end

  # holder field
  test "changeset with missing holder" do
    attrs = Map.delete(@valid_attrs, :holder)

    changeset = Card.changeset(%Card{}, attrs)
    assert changeset.errors == [holder: {"can't be blank",[]}]
  end
end
