package lib;

public class TaxFunction {

	private static final int BASE_TAX_THRESHOLD = 54000000;
    private static final int MARRIED_BONUS = 4500000;
    private static final int CHILD_BONUS_PER_CHILD = 1500000;
    private static final double TAX_RATE = 0.05;
	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 */
	
	
	 public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorked, int deductible, boolean isMarried, int numberOfChildren) {
        int tax = 0;

        if (numberOfMonthsWorked > 12) {
            System.err.println("More than 12 months working per year");
        }

        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);

        int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthsWorked;
        int taxableIncome = totalIncome - deductible - nonTaxableIncome;

        tax = (int) Math.round(TAX_RATE * taxableIncome);

        return Math.max(tax, 0); 
    }

    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        int nonTaxableIncome = BASE_TAX_THRESHOLD;
        if (isMarried) {
            nonTaxableIncome += MARRIED_BONUS;
        }
        nonTaxableIncome += Math.min(numberOfChildren, 3) * CHILD_BONUS_PER_CHILD;
        return nonTaxableIncome;
    }
}
