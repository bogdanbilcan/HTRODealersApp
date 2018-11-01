
package com.custom.logika.htro.dealersapp.model;

public class StocItem {

	protected int HTRO_CAR_NO;
	protected int RES_DEALER_ID;
	protected String DEALER_NAME;
	protected int AN_FABRICATIE_CIV;
	protected String TIP_AUTOVEHICUL;
	protected String COD_CULOARE_EXTERIOR;
	protected String DESC_CULOARE_EXTERIOR;
	protected String VOPSEA_METALIZATA;
	protected String CULOARE_INTERIOR;
	protected String OBSERVATII;
	protected String LOCATIE;
	protected String OMOLOGARE_IND;
	protected String LUNA_SOSIRE_IN_TARA;
	protected String REZERVATA;
	protected String DATA_EXPIRARE_REZ;
	protected String INVOICED;
	protected String PRODUCT_TYPE;

	public StocItem(
		int hTRO_CAR_NO, int rES_DEALER_ID, String dEALER_NAME, int aN_FABRICATIE_CIV, String tIP_AUTOVEHICUL, String cOD_CULOARE_EXTERIOR,
		String dESC_CULOARE_EXTERIOR, String vOPSEA_METALIZATA, String cULOARE_INTERIOR, String oBSERVATII, String lOCATIE,
		String oMOLOGARE_IND, String lUNA_SOSIRE_IN_TARA, String rEZERVATA, String dATA_EXPIRARE_REZ, String iNVOICED,
		String pRODUCT_TYPE) {
		super();
		HTRO_CAR_NO = hTRO_CAR_NO;
		RES_DEALER_ID = rES_DEALER_ID;
		DEALER_NAME = dEALER_NAME;
		AN_FABRICATIE_CIV = aN_FABRICATIE_CIV;
		TIP_AUTOVEHICUL = tIP_AUTOVEHICUL;
		COD_CULOARE_EXTERIOR = cOD_CULOARE_EXTERIOR;
		DESC_CULOARE_EXTERIOR = dESC_CULOARE_EXTERIOR;
		VOPSEA_METALIZATA = vOPSEA_METALIZATA;
		CULOARE_INTERIOR = cULOARE_INTERIOR;
		OBSERVATII = oBSERVATII;
		LOCATIE = lOCATIE;
		OMOLOGARE_IND = oMOLOGARE_IND;
		LUNA_SOSIRE_IN_TARA = lUNA_SOSIRE_IN_TARA;
		REZERVATA = rEZERVATA;
		DATA_EXPIRARE_REZ = dATA_EXPIRARE_REZ;
		INVOICED = iNVOICED;
		PRODUCT_TYPE = pRODUCT_TYPE;
	}

	public StocItem() {
	}

	@Override
	public String toString() {

		return "StocItem [HTRO_CAR_NO=" + HTRO_CAR_NO + ", RES_DEALER_ID=" + RES_DEALER_ID + ", DEALER_NAME=" + DEALER_NAME +
			", AN_FABRICATIE_CIV=" + AN_FABRICATIE_CIV + ", TIP_AUTOVEHICUL=" + TIP_AUTOVEHICUL + ", COD_CULOARE_EXTERIOR=" +
			COD_CULOARE_EXTERIOR + ", DESC_CULOARE_EXTERIOR=" + DESC_CULOARE_EXTERIOR + ", VOPSEA_METALIZATA=" + VOPSEA_METALIZATA +
			", CULOARE_INTERIOR=" + CULOARE_INTERIOR + ", OBSERVATII=" + OBSERVATII + ", LOCATIE=" + LOCATIE + ", OMOLOGARE_IND=" +
			OMOLOGARE_IND + ", LUNA_SOSIRE_IN_TARA=" + LUNA_SOSIRE_IN_TARA + ", REZERVATA=" + REZERVATA + ", DATA_EXPIRARE_REZ=" +
			DATA_EXPIRARE_REZ + ", INVOICED=" + INVOICED + ", PRODUCT_TYPE=" + PRODUCT_TYPE + "]";
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + AN_FABRICATIE_CIV;
		result = prime * result + ((COD_CULOARE_EXTERIOR == null) ? 0 : COD_CULOARE_EXTERIOR.hashCode());
		result = prime * result + ((CULOARE_INTERIOR == null) ? 0 : CULOARE_INTERIOR.hashCode());
		result = prime * result + ((DATA_EXPIRARE_REZ == null) ? 0 : DATA_EXPIRARE_REZ.hashCode());
		result = prime * result + ((DEALER_NAME == null) ? 0 : DEALER_NAME.hashCode());
		result = prime * result + ((DESC_CULOARE_EXTERIOR == null) ? 0 : DESC_CULOARE_EXTERIOR.hashCode());
		result = prime * result + HTRO_CAR_NO;
		result = prime * result + ((INVOICED == null) ? 0 : INVOICED.hashCode());
		result = prime * result + ((LOCATIE == null) ? 0 : LOCATIE.hashCode());
		result = prime * result + ((LUNA_SOSIRE_IN_TARA == null) ? 0 : LUNA_SOSIRE_IN_TARA.hashCode());
		result = prime * result + ((OBSERVATII == null) ? 0 : OBSERVATII.hashCode());
		result = prime * result + ((OMOLOGARE_IND == null) ? 0 : OMOLOGARE_IND.hashCode());
		result = prime * result + ((PRODUCT_TYPE == null) ? 0 : PRODUCT_TYPE.hashCode());
		result = prime * result + RES_DEALER_ID;
		result = prime * result + ((REZERVATA == null) ? 0 : REZERVATA.hashCode());
		result = prime * result + ((TIP_AUTOVEHICUL == null) ? 0 : TIP_AUTOVEHICUL.hashCode());
		result = prime * result + ((VOPSEA_METALIZATA == null) ? 0 : VOPSEA_METALIZATA.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		StocItem other = (StocItem) obj;
		if (AN_FABRICATIE_CIV != other.AN_FABRICATIE_CIV) return false;
		if (COD_CULOARE_EXTERIOR == null) {
			if (other.COD_CULOARE_EXTERIOR != null) return false;
		}
		else if (!COD_CULOARE_EXTERIOR.equals(other.COD_CULOARE_EXTERIOR)) return false;
		if (CULOARE_INTERIOR == null) {
			if (other.CULOARE_INTERIOR != null) return false;
		}
		else if (!CULOARE_INTERIOR.equals(other.CULOARE_INTERIOR)) return false;
		if (DATA_EXPIRARE_REZ == null) {
			if (other.DATA_EXPIRARE_REZ != null) return false;
		}
		else if (!DATA_EXPIRARE_REZ.equals(other.DATA_EXPIRARE_REZ)) return false;
		if (DEALER_NAME == null) {
			if (other.DEALER_NAME != null) return false;
		}
		else if (!DEALER_NAME.equals(other.DEALER_NAME)) return false;
		if (DESC_CULOARE_EXTERIOR == null) {
			if (other.DESC_CULOARE_EXTERIOR != null) return false;
		}
		else if (!DESC_CULOARE_EXTERIOR.equals(other.DESC_CULOARE_EXTERIOR)) return false;
		if (HTRO_CAR_NO != other.HTRO_CAR_NO) return false;
		if (INVOICED == null) {
			if (other.INVOICED != null) return false;
		}
		else if (!INVOICED.equals(other.INVOICED)) return false;
		if (LOCATIE == null) {
			if (other.LOCATIE != null) return false;
		}
		else if (!LOCATIE.equals(other.LOCATIE)) return false;
		if (LUNA_SOSIRE_IN_TARA == null) {
			if (other.LUNA_SOSIRE_IN_TARA != null) return false;
		}
		else if (!LUNA_SOSIRE_IN_TARA.equals(other.LUNA_SOSIRE_IN_TARA)) return false;
		if (OBSERVATII == null) {
			if (other.OBSERVATII != null) return false;
		}
		else if (!OBSERVATII.equals(other.OBSERVATII)) return false;
		if (OMOLOGARE_IND == null) {
			if (other.OMOLOGARE_IND != null) return false;
		}
		else if (!OMOLOGARE_IND.equals(other.OMOLOGARE_IND)) return false;
		if (PRODUCT_TYPE == null) {
			if (other.PRODUCT_TYPE != null) return false;
		}
		else if (!PRODUCT_TYPE.equals(other.PRODUCT_TYPE)) return false;
		if (RES_DEALER_ID != other.RES_DEALER_ID) return false;
		if (REZERVATA == null) {
			if (other.REZERVATA != null) return false;
		}
		else if (!REZERVATA.equals(other.REZERVATA)) return false;
		if (TIP_AUTOVEHICUL == null) {
			if (other.TIP_AUTOVEHICUL != null) return false;
		}
		else if (!TIP_AUTOVEHICUL.equals(other.TIP_AUTOVEHICUL)) return false;
		if (VOPSEA_METALIZATA == null) {
			if (other.VOPSEA_METALIZATA != null) return false;
		}
		else if (!VOPSEA_METALIZATA.equals(other.VOPSEA_METALIZATA)) return false;
		return true;
	}

	public String getDEALER_NAME() {

		return DEALER_NAME;
	}

	public void setDEALER_NAME(String dEALER_NAME) {

		DEALER_NAME = dEALER_NAME;
	}

	public String getCOD_CULOARE_EXTERIOR() {

		return COD_CULOARE_EXTERIOR;
	}

	public void setCOD_CULOARE_EXTERIOR(String cOD_CULOARE_EXTERIOR) {

		COD_CULOARE_EXTERIOR = cOD_CULOARE_EXTERIOR;
	}

	public int getHTRO_CAR_NO() {

		return HTRO_CAR_NO;
	}

	public void setHTRO_CAR_NO(int hTRO_CAR_NO) {

		HTRO_CAR_NO = hTRO_CAR_NO;
	}

	public int getRES_DEALER_ID() {

		return RES_DEALER_ID;
	}

	public void setRES_DEALER_ID(int rES_DEALER_ID) {

		RES_DEALER_ID = rES_DEALER_ID;
	}

	public int getAN_FABRICATIE_CIV() {

		return AN_FABRICATIE_CIV;
	}

	public void setAN_FABRICATIE_CIV(int aN_FABRICATIE_CIV) {

		AN_FABRICATIE_CIV = aN_FABRICATIE_CIV;
	}

	public String getTIP_AUTOVEHICUL() {

		return TIP_AUTOVEHICUL;
	}

	public void setTIP_AUTOVEHICUL(String tIP_AUTOVEHICUL) {

		TIP_AUTOVEHICUL = tIP_AUTOVEHICUL;
	}

	public String getDESC_CULOARE_EXTERIOR() {

		return DESC_CULOARE_EXTERIOR;
	}

	public void setDESC_CULOARE_EXTERIOR(String dESC_CULOARE_EXTERIOR) {

		DESC_CULOARE_EXTERIOR = dESC_CULOARE_EXTERIOR;
	}

	public String getVOPSEA_METALIZATA() {

		return VOPSEA_METALIZATA;
	}

	public void setVOPSEA_METALIZATA(String vOPSEA_METALIZATA) {

		VOPSEA_METALIZATA = vOPSEA_METALIZATA;
	}

	public String getCULOARE_INTERIOR() {

		return CULOARE_INTERIOR;
	}

	public void setCULOARE_INTERIOR(String cULOARE_INTERIOR) {

		CULOARE_INTERIOR = cULOARE_INTERIOR;
	}

	public String getOBSERVATII() {

		return OBSERVATII;
	}

	public void setOBSERVATII(String oBSERVATII) {

		OBSERVATII = oBSERVATII;
	}

	public String getLOCATIE() {

		return LOCATIE;
	}

	public void setLOCATIE(String lOCATIE) {

		LOCATIE = lOCATIE;
	}

	public String getOMOLOGARE_IND() {

		return OMOLOGARE_IND;
	}

	public void setOMOLOGARE_IND(String oMOLOGARE_IND) {

		OMOLOGARE_IND = oMOLOGARE_IND;
	}

	public String getLUNA_SOSIRE_IN_TARA() {

		return LUNA_SOSIRE_IN_TARA;
	}

	public void setLUNA_SOSIRE_IN_TARA(String lUNA_SOSIRE_IN_TARA) {

		LUNA_SOSIRE_IN_TARA = lUNA_SOSIRE_IN_TARA;
	}

	public String getREZERVATA() {

		return REZERVATA;
	}

	public void setREZERVATA(String rEZERVATA) {

		REZERVATA = rEZERVATA;
	}

	public String getDATA_EXPIRARE_REZ() {

		return DATA_EXPIRARE_REZ;
	}

	public void setDATA_EXPIRARE_REZ(String dATA_EXPIRARE_REZ) {

		DATA_EXPIRARE_REZ = dATA_EXPIRARE_REZ;
	}

	public String getINVOICED() {

		return INVOICED;
	}

	public void setINVOICED(String iNVOICED) {

		INVOICED = iNVOICED;
	}

	public String getPRODUCT_TYPE() {

		return PRODUCT_TYPE;
	}

	public void setPRODUCT_TYPE(String pRODUCT_TYPE) {

		PRODUCT_TYPE = pRODUCT_TYPE;
	}

}
