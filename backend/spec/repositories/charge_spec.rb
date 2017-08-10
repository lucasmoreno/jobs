RSpec.describe ::Repositories::Charge do
  describe '.create' do
    subject { described_class.create(charge) }

    let(:charge) { FactoryGirl.build :charge }
    let(:uuid) { 'le_uuid' }

    before do
      allow(SecureRandom).to receive(:uuid).and_return(uuid)
    end

    it { expect(subject.id).to eq(uuid) }
    it { expect(subject.card_id).to eq(uuid) }
  end
end
