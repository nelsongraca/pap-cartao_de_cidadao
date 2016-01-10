/*
 * Projeto para o artigo "Extrair dados do cartão de cidadão com a linguagem de programação JAVA"
 * Para a comunidade e revista electrónica Portugal-a-Programar (http://www.portugal-a-programar.pt/)
 */
package PaPCC;

import static java.lang.Math.abs;

/**
 *
 * @author Ricardo Ramos Cabral
 */
public class PortugueseID {

    /**
     * Validation number of European PortugueseID ID
     * @param idNumber number id
     * @param checkDigit number id Check Digit
     * @return true if is valid
     */
    public static boolean validatorCitizenID(String idNumber, String checkDigit) {
        String BIformated;
        if ((idNumber.length() < 6) || (idNumber.length() > 8)) {
            return false;
        }
        if (checkDigit.length() != 1) {
            return false;
        }
        if (idNumber.length() == 8) {
            BIformated = idNumber + checkDigit;
        } else {
            BIformated = '0' + idNumber + checkDigit;
        }
        int val = 0;
        for (int pos = 0; pos < 8; ++pos) {
            val += Integer.parseInt(BIformated.charAt(pos) + "", 10) * (9 - pos);
        }
        int ctl = 0;
        if ((val % 11) != 0) {
            ctl = (11 - val % 11) % 10;
        }
        return ctl == Integer.parseInt(BIformated.charAt(8) + "", 10);
    }

    /**
     * Validation number of European PortugueseID contributors
     *
     * @param taxNumber
     * @return true if is valid
     * @throws Exception if the received variable contains a non-numeric character
     */
    public static boolean validatorTaxNumber(String taxNumber) throws Exception {
        int checkDigit = 0;
        if (taxNumber.length() != 9) {
            return false;
        } else {
            char c1 = taxNumber.charAt(0);
            if (c1 == '1' || c1 == '2' || c1 == '5' || c1 == '6' || c1 == '7' || c1 == '8' || c1 == '9') {
                checkDigit = Integer.parseInt("" + c1, 10) * 9;
                for (int i = 2; i < 9; i++) {
                    checkDigit += Integer.parseInt("" + taxNumber.charAt(i - 1), 10) * (10 - i);
                }
                checkDigit = 11 - (checkDigit % 11);
                if (checkDigit >= 10) {
                    checkDigit = 0;
                }
                return checkDigit == Integer.parseInt("" + taxNumber.charAt(8));
            } else {
                return false;
            }
        }
    }

    /**
     * Validation number of European PortugueseID Citizen Card Source: https://www.cartaodecidadao.pt/images/stories/Algoritmo_Num_Documento_CC.pdf
     *
     * @param documentNumber
     * @param documentCheckDigit
     * @return true if is valid
     * @throws Exception 
     */
    public static boolean validatorCitizenCardNumber(String documentNumber, String documentCheckDigit) throws Exception {
        if (documentNumber.length() == 8 && documentCheckDigit.length() == 1) {
            return PortugueseID.validatorCitizenID(documentNumber, documentCheckDigit);
        } else if (documentNumber.length() == 8 && documentCheckDigit.length() == 4) {
            documentNumber = documentNumber + documentCheckDigit;
            return new PortugueseID().vCitizenCardNumber(documentNumber);
        } else {
            return false;
        }

    }

    /**
     * Validation number of European PortugueseID Citizen Card
     * @param documentNumber Citizen card number
     * @return true if is valid
     * @throws Exception if the received variable contains a non-numeric character
     */
    public boolean vCitizenCardNumber(String documentNumber) throws Exception {
        int sum = 0;
        boolean secondDigit = false;

        if (documentNumber.length() != 12) {
            throw new Exception("Invalid size for document number.");
        }
        for (int i = documentNumber.length() - 1; i >= 0; --i) {
            int valor = getCitizenCardNumberFromChar(documentNumber.charAt(i));
            if (secondDigit) {
                valor *= 2;
                if (valor > 9) {
                    valor -= 9;
                }
            }
            sum += valor;
            secondDigit = !secondDigit;
        }
        return (sum % 10) == 0;
    }

    /**
     * Validation number of European PortugueseID Citizen Card Check Digit
     * @param Citizen card number check digit
     * @return number 
     */
    private int getCitizenCardNumberFromChar(char letter) {
        switch (letter) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
            case 'G':
                return 16;
            case 'H':
                return 17;
            case 'I':
                return 18;
            case 'J':
                return 19;
            case 'K':
                return 20;
            case 'L':
                return 21;
            case 'M':
                return 22;
            case 'N':
                return 23;
            case 'O':
                return 24;
            case 'P':
                return 25;
            case 'Q':
                return 26;
            case 'R':
                return 27;
            case 'S':
                return 28;
            case 'T':
                return 29;
            case 'U':
                return 30;
            case 'V':
                return 31;
            case 'W':
                return 32;
            case 'X':
                return 33;
            case 'Y':
                return 34;
            case 'Z':
                return 35;
            default:
                return 0;
        }
    }

    /**
     * Validate identification number of European PortugueseID Social Security number Source: http://www.galhano.com/blog/wp-content/2007/06/NISS.pdf
     *
     * @param ssID Social Security number 
     * @return true if is valid
     * @throws Exception if the received variable contains a non-numeric character
     */
    public static boolean validateSocialSecurityID(String ssID) throws Exception {
        int val = 0;
        int[] array = {29, 23, 19, 17, 13, 11, 7, 5, 3, 2};
        if (ssID.length() != 11) {
            return false;
        } else {
            for (int i = 0; i < 10; i++) {
                val += Integer.parseInt(ssID.charAt(i) + "", 10) * array[i];
            }
        }
        val = val % 10;
        return (9 - val) == Integer.parseInt(ssID.charAt(10) + "", 10);

    }

    /**
     * Converts the old European PortugueseID Social Security number to the new number.
     *
     * @param ssID Social Security number
     * @return true if is valid
     * @throws Exception if the received variable contains a non-numeric character
     */
    public static String newSocialSecurityID(String ssID) throws Exception {
        int val = 0;
        int[] array = {29, 23, 19, 17, 13, 11, 7, 5, 3, 2};
        ssID = '1' + ssID;
        for (int i = 0; i < 10; i++) {
            val += Integer.parseInt(ssID.charAt(i) + "", 10) * array[i];
        }
        val = val % 10;
        val = abs(9 - val);
        return ssID + val;
    }
    
     /**
     * Portuguese validation procedures (Portuguese) http://www.bportugal.pt/pt-PT/pagamentos/InstrumentosdePagamento/IBAN/Documents/International%20Bank%20Account%20Number_pt.pdf
     *
     * @param nib
     * @return true if is valid
     */
    public static Boolean verefyNIB(String nib) {
        try {
            if (nib.length() != 21) {
                return false;
            } else {
                char[] toValidate = nib.substring(0, 19).toCharArray();
                Integer checkDigit = Integer.valueOf(nib.substring(19));
                Integer[] wi = {73, 17, 89, 38, 62, 45, 53, 15, 50, 5, 49, 34, 81, 76, 27, 90, 9, 30, 3};
                Integer sum = 0;
                for (int i = 0; i < 19; i++) {
                    sum += Character.digit(toValidate[i], 10) * wi[i];
                }
                return checkDigit.equals(98 - (sum % 97));
            }
        } catch (Exception ex) {
            throw new NumberFormatException("Invalid NIB");
        }
    }
}
