package com.logika.custom.honda.dealerapp.model;

import java.util.Arrays;
import java.util.List;
public class PortofoliuItem {

	public PortofoliuItem() {
	}

	public PortofoliuItem(int hTRO_CAR_NO, String dATA_REZ_SAU_FACTURA, String dATA_EXPIRARE, int rES_DEALER_ID,
			String tIP_LINIE, String lOCATIE, String lUNA_PRODUCTIE,
			String lUNA_SOSIRE_IN_TARA, String cOD_MODEL,
			String tIP_AUTOVEHICUL, String cOD_CULOARE_EXT,
			String cULOARE_EXTERIOR, String cULOARE_INTERIOR, String oBSERVATII,
			String nUME_CLIENT, String nUME_VANZATOR, String vIN,
			String eNGINE_NO, int aN_FABRICATIE_CF_CIV,
			String oMOLOGARE_INDIVIDUALA, int pRET_LISTA, int dISCOUNT_STANDARD,
			int dISCOUNT_SUPLIMENTAR, int tRUSA_LEGISLATIVA, int pRET_FINAL,
			int aVANS_PLATIT, int rEST_DE_PLATA, int cUSTOMER_TRX_ID,
			String rEZ_CUST_ID, int sOLD_CUST_ID) {

	HTRO_CAR_NO = hTRO_CAR_NO; DATA_REZ_SAU_FACTURA = dATA_REZ_SAU_FACTURA;
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
	}

	public int getAN_FABRICATIE_CF_CIV() {
		return AN_FABRICATIE_CF_CIV;
	}

	public int getAVANS_PLATIT() {
		return AVANS_PLATIT;
	}

	public String getCOD_CULOARE_EXT() {
		return COD_CULOARE_EXT;
	}

	public String getCOD_MODEL() {
		return COD_MODEL;
	}

	public String getCULOARE_EXTERIOR() {
		return CULOARE_EXTERIOR;
	}

	public String getCULOARE_INTERIOR() {
		return CULOARE_INTERIOR;
	}

	public int getCUSTOMER_TRX_ID() {
		return CUSTOMER_TRX_ID;
	}

	public String getDATA_EXPIRARE() {
		return DATA_EXPIRARE;
	}

	public String getDATA_REZ_SAU_FACTURA() {
		return DATA_REZ_SAU_FACTURA;
	}

	public int getDISCOUNT_STANDARD() {
		return DISCOUNT_STANDARD;
	}

	public int getDISCOUNT_SUPLIMENTAR() {
		return DISCOUNT_SUPLIMENTAR;
	}

	public String getENGINE_NO() {
		return ENGINE_NO;
	}

	public int getHTRO_CAR_NO() {
		return HTRO_CAR_NO;
	}

	public String getLOCATIE() {
		return LOCATIE;
	}

	public String getLUNA_PRODUCTIE() {
		return LUNA_PRODUCTIE;
	}

	public String getLUNA_SOSIRE_IN_TARA() {
		return LUNA_SOSIRE_IN_TARA;
	}

	public String getNUME_CLIENT() {
		return NUME_CLIENT;
	}

	public String getNUME_VANZATOR() {
		return NUME_VANZATOR;
	}

	public String getOBSERVATII() {
		return OBSERVATII;
	}

	public String getOMOLOGARE_INDIVIDUALA() {
		return OMOLOGARE_INDIVIDUALA;
	}

	public int getPRET_FINAL() {
		return PRET_FINAL;
	}

	public int getPRET_LISTA() {
		return PRET_LISTA;
	}

	public int getRES_DEALER_ID() {
		return RES_DEALER_ID;
	}

	public int getREST_DE_PLATA() {
		return REST_DE_PLATA;
	}

	public String getREZ_CUST_ID() {
		return REZ_CUST_ID;
	}

	public int getSOLD_CUST_ID() {
		return SOLD_CUST_ID;
	}

	public String getTIP_AUTOVEHICUL() {
		return TIP_AUTOVEHICUL;
	}

	public String getTIP_LINIE() {
		return TIP_LINIE;
	}

	public int getTRUSA_LEGISLATIVA() {
		return TRUSA_LEGISLATIVA;
	}

	public String getVIN() {
		return VIN;
	}

	public void setAN_FABRICATIE_CF_CIV(int aN_FABRICATIE_CF_CIV) {
		AN_FABRICATIE_CF_CIV = aN_FABRICATIE_CF_CIV;
	}

	public void setAVANS_PLATIT(int aVANS_PLATIT) {
		AVANS_PLATIT = aVANS_PLATIT;
	}

	public void setCOD_CULOARE_EXT(String cOD_CULOARE_EXT) {
		COD_CULOARE_EXT = cOD_CULOARE_EXT;
	}

	public void setCOD_MODEL(String cOD_MODEL) {
		COD_MODEL = cOD_MODEL;
	}

	public void setCULOARE_EXTERIOR(String cULOARE_EXTERIOR) {
		CULOARE_EXTERIOR = cULOARE_EXTERIOR;
	}

	public void setCULOARE_INTERIOR(String cULOARE_INTERIOR) {
		CULOARE_INTERIOR = cULOARE_INTERIOR;
	}

	public void setCUSTOMER_TRX_ID(int cUSTOMER_TRX_ID) {
		CUSTOMER_TRX_ID = cUSTOMER_TRX_ID;
	}

	public void setDATA_EXPIRARE(String dATA_EXPIRARE) {
		DATA_EXPIRARE = dATA_EXPIRARE;
	}

	public void setDATA_REZ_SAU_FACTURA(String dATA_REZ_SAU_FACTURA) {
		DATA_REZ_SAU_FACTURA = dATA_REZ_SAU_FACTURA;
	}

	public void setDISCOUNT_STANDARD(int dISCOUNT_STANDARD) {
		DISCOUNT_STANDARD = dISCOUNT_STANDARD;
	}

	public void setDISCOUNT_SUPLIMENTAR(int dISCOUNT_SUPLIMENTAR) {
		DISCOUNT_SUPLIMENTAR = dISCOUNT_SUPLIMENTAR;
	}

	public void setENGINE_NO(String eNGINE_NO) {
		ENGINE_NO = eNGINE_NO;
	}

	public void setHTRO_CAR_NO(int hTRO_CAR_NO) {
		HTRO_CAR_NO = hTRO_CAR_NO;
	}

	public void setLOCATIE(String lOCATIE) {
		LOCATIE = lOCATIE;
	}

	public void setLUNA_PRODUCTIE(String lUNA_PRODUCTIE) {
		LUNA_PRODUCTIE = lUNA_PRODUCTIE;
	}

	public void setLUNA_SOSIRE_IN_TARA(String lUNA_SOSIRE_IN_TARA) {
		LUNA_SOSIRE_IN_TARA = lUNA_SOSIRE_IN_TARA;
	}

	public void setNUME_CLIENT(String nUME_CLIENT) {
		NUME_CLIENT = nUME_CLIENT;
	}

	public void setNUME_VANZATOR(String nUME_VANZATOR) {
		NUME_VANZATOR = nUME_VANZATOR;
	}

	public void setOBSERVATII(String oBSERVATII) {
		OBSERVATII = oBSERVATII;
	}

	public void setOMOLOGARE_INDIVIDUALA(String oMOLOGARE_INDIVIDUALA) {
		OMOLOGARE_INDIVIDUALA = oMOLOGARE_INDIVIDUALA;
	}

	public void setPRET_FINAL(int pRET_FINAL) {
		PRET_FINAL = pRET_FINAL;
	}

	public void setPRET_LISTA(int pRET_LISTA) {
		PRET_LISTA = pRET_LISTA;
	}

	public void setRES_DEALER_ID(int rES_DEALER_ID) {
		RES_DEALER_ID = rES_DEALER_ID;
	}

	public void setREST_DE_PLATA(int rEST_DE_PLATA) {
		REST_DE_PLATA = rEST_DE_PLATA;
	}

	public void setREZ_CUST_ID(String rEZ_CUST_ID) {
		REZ_CUST_ID = rEZ_CUST_ID;
	}

	public void setSOLD_CUST_ID(int sOLD_CUST_ID) {
		SOLD_CUST_ID = sOLD_CUST_ID;
	}

	public void setTIP_AUTOVEHICUL(String tIP_AUTOVEHICUL) {
		TIP_AUTOVEHICUL = tIP_AUTOVEHICUL;
	}

	public void setTIP_LINIE(String tIP_LINIE) {
		TIP_LINIE = tIP_LINIE;
	}

	public void setTRUSA_LEGISLATIVA(int tRUSA_LEGISLATIVA) {
		TRUSA_LEGISLATIVA = tRUSA_LEGISLATIVA;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}

	public List<String> toList() {
		List<String> portofoliuDataList = Arrays.asList(String.valueOf(HTRO_CAR_NO), TIP_LINIE, DATA_REZ_SAU_FACTURA,
				DATA_EXPIRARE, LOCATIE, LUNA_SOSIRE_IN_TARA, COD_MODEL,
				TIP_AUTOVEHICUL, COD_CULOARE_EXT, CULOARE_EXTERIOR,
				CULOARE_INTERIOR, NUME_CLIENT, NUME_VANZATOR, VIN, ENGINE_NO,
				String.valueOf(AN_FABRICATIE_CF_CIV), OBSERVATII,
				OMOLOGARE_INDIVIDUALA, String.valueOf(PRET_LISTA),
				String.valueOf(DISCOUNT_STANDARD),
				String.valueOf(DISCOUNT_SUPLIMENTAR),
				String.valueOf(TRUSA_LEGISLATIVA), String.valueOf(PRET_FINAL),
				String.valueOf(AVANS_PLATIT), String.valueOf(REST_DE_PLATA));

		return portofoliuDataList;
	}

	protected int AN_FABRICATIE_CF_CIV;

	protected int AVANS_PLATIT;

	protected String COD_CULOARE_EXT;

	protected String COD_MODEL;

	protected String CULOARE_EXTERIOR;

	protected String CULOARE_INTERIOR;

	protected int CUSTOMER_TRX_ID;

	protected String DATA_EXPIRARE;

	protected String DATA_REZ_SAU_FACTURA;

	protected int DISCOUNT_STANDARD;

	protected int DISCOUNT_SUPLIMENTAR;

	protected String ENGINE_NO;

	protected int HTRO_CAR_NO;

	protected String LOCATIE;

	protected String LUNA_PRODUCTIE;

	protected String LUNA_SOSIRE_IN_TARA;

	protected String NUME_CLIENT;

	protected String NUME_VANZATOR;

	protected String OBSERVATII;

	protected String OMOLOGARE_INDIVIDUALA;

	protected int PRET_FINAL;

	protected int PRET_LISTA;

	protected int RES_DEALER_ID;

	protected int REST_DE_PLATA;

	protected String REZ_CUST_ID;

	protected int SOLD_CUST_ID;

	protected String TIP_AUTOVEHICUL;

	protected String TIP_LINIE;

	protected int TRUSA_LEGISLATIVA;

	protected String VIN;

}