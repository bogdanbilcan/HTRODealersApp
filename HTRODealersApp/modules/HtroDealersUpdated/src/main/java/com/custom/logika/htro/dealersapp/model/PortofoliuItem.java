
package com.custom.logika.htro.dealersapp.model;

import java.util.Arrays;
import java.util.List;

public class PortofoliuItem {

	protected int HTRO_CAR_NO;
	protected String DEALER;
	protected String DATA_REZ_SAU_FACTURA;
	protected String DATA_EXPIRARE;
	protected int RES_DEALER_ID;
	protected String TIP_LINIE;
	protected String LOCATIE;
	protected String LUNA_PRODUCTIE;
	protected String LUNA_SOSIRE_IN_TARA;
	protected String COD_MODEL;
	protected String TIP_AUTOVEHICUL;
	protected String COD_CULOARE_EXT;
	protected String CULOARE_EXTERIOR;
	protected String CULOARE_INTERIOR;
	protected String OBSERVATII;
	protected String NUME_CLIENT;
	protected String NUME_VANZATOR;
	protected String VIN;
	protected String ENGINE_NO;
	protected int AN_FABRICATIE_CF_CIV;
	protected String OMOLOGARE_INDIVIDUALA;
	protected int PRET_LISTA;
	protected int DISCOUNT_STANDARD;
	protected int DISCOUNT_SUPLIMENTAR;
	protected int TRUSA_LEGISLATIVA;
	protected int PRET_FINAL;
	protected int AVANS_PLATIT;
	protected int REST_DE_PLATA;
	protected int CUSTOMER_TRX_ID;
	protected String REZ_CUST_ID;
	protected int SOLD_CUST_ID;
	protected boolean PROFORMA;
	protected boolean TRANSPORT;

	public PortofoliuItem() {
	}

	public PortofoliuItem(
		int hTRO_CAR_NO, String dEALER, String dATA_REZ_SAU_FACTURA, String dATA_EXPIRARE, int rES_DEALER_ID, String tIP_LINIE,
		String lOCATIE, String lUNA_PRODUCTIE, String lUNA_SOSIRE_IN_TARA, String cOD_MODEL, String tIP_AUTOVEHICUL, String cOD_CULOARE_EXT,
		String cULOARE_EXTERIOR, String cULOARE_INTERIOR, String oBSERVATII, String nUME_CLIENT, String nUME_VANZATOR, String vIN,
		String eNGINE_NO, int aN_FABRICATIE_CF_CIV, String oMOLOGARE_INDIVIDUALA, int pRET_LISTA, int dISCOUNT_STANDARD,
		int dISCOUNT_SUPLIMENTAR, int tRUSA_LEGISLATIVA, int pRET_FINAL, int aVANS_PLATIT, int rEST_DE_PLATA, int cUSTOMER_TRX_ID,
		String rEZ_CUST_ID, int sOLD_CUST_ID, boolean pROFORMA, boolean tRANSPORT) {
		super();
		HTRO_CAR_NO = hTRO_CAR_NO;
		DEALER = dEALER;
		DATA_REZ_SAU_FACTURA = dATA_REZ_SAU_FACTURA;
		DATA_EXPIRARE = dATA_EXPIRARE;
		RES_DEALER_ID = rES_DEALER_ID;
		TIP_LINIE = tIP_LINIE;
		LOCATIE = lOCATIE;
		LUNA_PRODUCTIE = lUNA_PRODUCTIE;
		LUNA_SOSIRE_IN_TARA = lUNA_SOSIRE_IN_TARA;
		COD_MODEL = cOD_MODEL;
		TIP_AUTOVEHICUL = tIP_AUTOVEHICUL;
		COD_CULOARE_EXT = cOD_CULOARE_EXT;
		CULOARE_EXTERIOR = cULOARE_EXTERIOR;
		CULOARE_INTERIOR = cULOARE_INTERIOR;
		OBSERVATII = oBSERVATII;
		NUME_CLIENT = nUME_CLIENT;
		NUME_VANZATOR = nUME_VANZATOR;
		VIN = vIN;
		ENGINE_NO = eNGINE_NO;
		AN_FABRICATIE_CF_CIV = aN_FABRICATIE_CF_CIV;
		OMOLOGARE_INDIVIDUALA = oMOLOGARE_INDIVIDUALA;
		PRET_LISTA = pRET_LISTA;
		DISCOUNT_STANDARD = dISCOUNT_STANDARD;
		DISCOUNT_SUPLIMENTAR = dISCOUNT_SUPLIMENTAR;
		TRUSA_LEGISLATIVA = tRUSA_LEGISLATIVA;
		PRET_FINAL = pRET_FINAL;
		AVANS_PLATIT = aVANS_PLATIT;
		REST_DE_PLATA = rEST_DE_PLATA;
		CUSTOMER_TRX_ID = cUSTOMER_TRX_ID;
		REZ_CUST_ID = rEZ_CUST_ID;
		SOLD_CUST_ID = sOLD_CUST_ID;
		PROFORMA = pROFORMA;
		TRANSPORT = tRANSPORT;

	}

	public List<String> toList() {

		List<String> portofoliuDataList = Arrays.asList(
			String.valueOf(HTRO_CAR_NO), DEALER, TIP_LINIE, DATA_REZ_SAU_FACTURA, DATA_EXPIRARE, LOCATIE, LUNA_SOSIRE_IN_TARA, COD_MODEL,
			TIP_AUTOVEHICUL, COD_CULOARE_EXT, CULOARE_EXTERIOR, CULOARE_INTERIOR, NUME_CLIENT, NUME_VANZATOR, VIN, ENGINE_NO,
			String.valueOf(AN_FABRICATIE_CF_CIV), OBSERVATII, OMOLOGARE_INDIVIDUALA, String.valueOf(PRET_LISTA),
			String.valueOf(DISCOUNT_STANDARD), String.valueOf(DISCOUNT_SUPLIMENTAR), String.valueOf(TRUSA_LEGISLATIVA),
			String.valueOf(PRET_FINAL), String.valueOf(AVANS_PLATIT), String.valueOf(REST_DE_PLATA));
		return portofoliuDataList;
	}

	public List<String> toList2() {

		List<String> portofoliuDataList = Arrays.asList(
			String.valueOf(HTRO_CAR_NO), TIP_LINIE, DATA_REZ_SAU_FACTURA, DATA_EXPIRARE, LOCATIE, LUNA_SOSIRE_IN_TARA, COD_MODEL,
			TIP_AUTOVEHICUL, COD_CULOARE_EXT, CULOARE_EXTERIOR, CULOARE_INTERIOR, NUME_CLIENT, NUME_VANZATOR, VIN, ENGINE_NO,
			String.valueOf(AN_FABRICATIE_CF_CIV), OBSERVATII, OMOLOGARE_INDIVIDUALA, String.valueOf(PRET_LISTA),
			String.valueOf(DISCOUNT_STANDARD), String.valueOf(DISCOUNT_SUPLIMENTAR), String.valueOf(TRUSA_LEGISLATIVA),
			String.valueOf(PRET_FINAL), String.valueOf(AVANS_PLATIT), String.valueOf(REST_DE_PLATA));
		return portofoliuDataList;
	}

	@Override
	public String toString() {

		return "[HTRO_CAR_NO=" + HTRO_CAR_NO + ", DEALER=" + DEALER + ", DATA_REZ_SAU_FACTURA=" + DATA_REZ_SAU_FACTURA +
			", DATA_EXPIRARE=" + DATA_EXPIRARE + ", RES_DEALER_ID=" + RES_DEALER_ID + ", TIP_LINIE=" + TIP_LINIE + ", LOCATIE=" + LOCATIE +
			", LUNA_PRODUCTIE=" + LUNA_PRODUCTIE + ", LUNA_SOSIRE_IN_TARA=" + LUNA_SOSIRE_IN_TARA + ", COD_MODEL=" + COD_MODEL +
			", TIP_AUTOVEHICUL=" + TIP_AUTOVEHICUL + ", COD_CULOARE_EXT=" + COD_CULOARE_EXT + ", CULOARE_EXTERIOR=" + CULOARE_EXTERIOR +
			", CULOARE_INTERIOR=" + CULOARE_INTERIOR + ", OBSERVATII=" + OBSERVATII + ", NUME_CLIENT=" + NUME_CLIENT + ", NUME_VANZATOR=" +
			NUME_VANZATOR + ", VIN=" + VIN + ", ENGINE_NO=" + ENGINE_NO + ", AN_FABRICATIE_CF_CIV=" + AN_FABRICATIE_CF_CIV +
			", OMOLOGARE_INDIVIDUALA=" + OMOLOGARE_INDIVIDUALA + ", PRET_LISTA=" + PRET_LISTA + ", DISCOUNT_STANDARD=" + DISCOUNT_STANDARD +
			", DISCOUNT_SUPLIMENTAR=" + DISCOUNT_SUPLIMENTAR + ", TRUSA_LEGISLATIVA=" + TRUSA_LEGISLATIVA + ", PRET_FINAL=" + PRET_FINAL +
			", AVANS_PLATIT=" + AVANS_PLATIT + ", REST_DE_PLATA=" + REST_DE_PLATA + ", CUSTOMER_TRX_ID=" + CUSTOMER_TRX_ID +
			", REZ_CUST_ID=" + REZ_CUST_ID + ", SOLD_CUST_ID=" + SOLD_CUST_ID + ", PROFORMA=" + PROFORMA + ", TRANSPORT=" + TRANSPORT + "]";
	}

	public boolean isPROFORMA() {

		return PROFORMA;
	}

	public void setPROFORMA(boolean pROFORMA) {

		PROFORMA = pROFORMA;
	}

	public boolean isTRANSPORT() {

		return TRANSPORT;
	}

	public void setTRANSPORT(boolean tRANSPORT) {

		TRANSPORT = tRANSPORT;
	}

	public String getDEALER() {

		return DEALER;
	}

	public void setDEALER(String dEALER) {

		DEALER = dEALER;
	}

	public int getHTRO_CAR_NO() {

		return HTRO_CAR_NO;
	}

	public void setHTRO_CAR_NO(int hTRO_CAR_NO) {

		HTRO_CAR_NO = hTRO_CAR_NO;
	}

	public String getDATA_REZ_SAU_FACTURA() {

		return DATA_REZ_SAU_FACTURA;
	}

	public void setDATA_REZ_SAU_FACTURA(String dATA_REZ_SAU_FACTURA) {

		DATA_REZ_SAU_FACTURA = dATA_REZ_SAU_FACTURA;
	}

	public String getDATA_EXPIRARE() {

		return DATA_EXPIRARE;
	}

	public void setDATA_EXPIRARE(String dATA_EXPIRARE) {

		DATA_EXPIRARE = dATA_EXPIRARE;
	}

	public int getRES_DEALER_ID() {

		return RES_DEALER_ID;
	}

	public void setRES_DEALER_ID(int rES_DEALER_ID) {

		RES_DEALER_ID = rES_DEALER_ID;
	}

	public String getTIP_LINIE() {

		return TIP_LINIE;
	}

	public void setTIP_LINIE(String tIP_LINIE) {

		TIP_LINIE = tIP_LINIE;
	}

	public String getLOCATIE() {

		return LOCATIE;
	}

	public void setLOCATIE(String lOCATIE) {

		LOCATIE = lOCATIE;
	}

	public String getLUNA_PRODUCTIE() {

		return LUNA_PRODUCTIE;
	}

	public void setLUNA_PRODUCTIE(String lUNA_PRODUCTIE) {

		LUNA_PRODUCTIE = lUNA_PRODUCTIE;
	}

	public String getLUNA_SOSIRE_IN_TARA() {

		return LUNA_SOSIRE_IN_TARA;
	}

	public void setLUNA_SOSIRE_IN_TARA(String lUNA_SOSIRE_IN_TARA) {

		LUNA_SOSIRE_IN_TARA = lUNA_SOSIRE_IN_TARA;
	}

	public String getCOD_MODEL() {

		return COD_MODEL;
	}

	public void setCOD_MODEL(String cOD_MODEL) {

		COD_MODEL = cOD_MODEL;
	}

	public String getTIP_AUTOVEHICUL() {

		return TIP_AUTOVEHICUL;
	}

	public void setTIP_AUTOVEHICUL(String tIP_AUTOVEHICUL) {

		TIP_AUTOVEHICUL = tIP_AUTOVEHICUL;
	}

	public String getCOD_CULOARE_EXT() {

		return COD_CULOARE_EXT;
	}

	public void setCOD_CULOARE_EXT(String cOD_CULOARE_EXT) {

		COD_CULOARE_EXT = cOD_CULOARE_EXT;
	}

	public String getCULOARE_EXTERIOR() {

		return CULOARE_EXTERIOR;
	}

	public void setCULOARE_EXTERIOR(String cULOARE_EXTERIOR) {

		CULOARE_EXTERIOR = cULOARE_EXTERIOR;
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

	public String getNUME_CLIENT() {

		return NUME_CLIENT;
	}

	public void setNUME_CLIENT(String nUME_CLIENT) {

		NUME_CLIENT = nUME_CLIENT;
	}

	public String getNUME_VANZATOR() {

		return NUME_VANZATOR;
	}

	public void setNUME_VANZATOR(String nUME_VANZATOR) {

		NUME_VANZATOR = nUME_VANZATOR;
	}

	public String getVIN() {

		return VIN;
	}

	public void setVIN(String vIN) {

		VIN = vIN;
	}

	public String getENGINE_NO() {

		return ENGINE_NO;
	}

	public void setENGINE_NO(String eNGINE_NO) {

		ENGINE_NO = eNGINE_NO;
	}

	public int getAN_FABRICATIE_CF_CIV() {

		return AN_FABRICATIE_CF_CIV;
	}

	public void setAN_FABRICATIE_CF_CIV(int aN_FABRICATIE_CF_CIV) {

		AN_FABRICATIE_CF_CIV = aN_FABRICATIE_CF_CIV;
	}

	public String getOMOLOGARE_INDIVIDUALA() {

		return OMOLOGARE_INDIVIDUALA;
	}

	public void setOMOLOGARE_INDIVIDUALA(String oMOLOGARE_INDIVIDUALA) {

		OMOLOGARE_INDIVIDUALA = oMOLOGARE_INDIVIDUALA;
	}

	public int getPRET_LISTA() {

		return PRET_LISTA;
	}

	public void setPRET_LISTA(int pRET_LISTA) {

		PRET_LISTA = pRET_LISTA;
	}

	public int getDISCOUNT_STANDARD() {

		return DISCOUNT_STANDARD;
	}

	public void setDISCOUNT_STANDARD(int dISCOUNT_STANDARD) {

		DISCOUNT_STANDARD = dISCOUNT_STANDARD;
	}

	public int getDISCOUNT_SUPLIMENTAR() {

		return DISCOUNT_SUPLIMENTAR;
	}

	public void setDISCOUNT_SUPLIMENTAR(int dISCOUNT_SUPLIMENTAR) {

		DISCOUNT_SUPLIMENTAR = dISCOUNT_SUPLIMENTAR;
	}

	public int getTRUSA_LEGISLATIVA() {

		return TRUSA_LEGISLATIVA;
	}

	public void setTRUSA_LEGISLATIVA(int tRUSA_LEGISLATIVA) {

		TRUSA_LEGISLATIVA = tRUSA_LEGISLATIVA;
	}

	public int getPRET_FINAL() {

		return PRET_FINAL;
	}

	public void setPRET_FINAL(int pRET_FINAL) {

		PRET_FINAL = pRET_FINAL;
	}

	public int getAVANS_PLATIT() {

		return AVANS_PLATIT;
	}

	public void setAVANS_PLATIT(int aVANS_PLATIT) {

		AVANS_PLATIT = aVANS_PLATIT;
	}

	public int getREST_DE_PLATA() {

		return REST_DE_PLATA;
	}

	public void setREST_DE_PLATA(int rEST_DE_PLATA) {

		REST_DE_PLATA = rEST_DE_PLATA;
	}

	public int getCUSTOMER_TRX_ID() {

		return CUSTOMER_TRX_ID;
	}

	public void setCUSTOMER_TRX_ID(int cUSTOMER_TRX_ID) {

		CUSTOMER_TRX_ID = cUSTOMER_TRX_ID;
	}

	public String getREZ_CUST_ID() {

		return REZ_CUST_ID;
	}

	public void setREZ_CUST_ID(String rEZ_CUST_ID) {

		REZ_CUST_ID = rEZ_CUST_ID;
	}

	public int getSOLD_CUST_ID() {

		return SOLD_CUST_ID;
	}

	public void setSOLD_CUST_ID(int sOLD_CUST_ID) {

		SOLD_CUST_ID = sOLD_CUST_ID;
	}

}
