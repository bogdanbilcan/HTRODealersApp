package com.logika.custom.honda.dealerapp.model;

public class StocItem {

	protected int HTRO_CAR_NO;
	protected int RES_DEALER_ID;
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

	public StocItem(int hTRO_CAR_NO, int rES_DEALER_ID, int aN_FABRICATIE_CIV, String tIP_AUTOVEHICUL,
			String cOD_CULOARE_EXTERIOR, String dESC_CULOARE_EXTERIOR, String vOPSEA_METALIZATA,
			String cULOARE_INTERIOR, String oBSERVATII, String lOCATIE, String oMOLOGARE_IND,
			String lUNA_SOSIRE_IN_TARA, String rEZERVATA, String dATA_EXPIRARE_REZ) {
		HTRO_CAR_NO = hTRO_CAR_NO;
		RES_DEALER_ID = rES_DEALER_ID;
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
	}

	public StocItem() {
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

	public String getCOD_CULOARE_EXTERIO() {
		return COD_CULOARE_EXTERIOR;
	}

	public void setCOD_CULOARE_EXTERIO(String cOD_CULOARE_EXTERIOR) {
		COD_CULOARE_EXTERIOR = cOD_CULOARE_EXTERIOR;
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

}
