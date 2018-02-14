package com.google.newsapi.config;

public class Constants
{
	public enum LANGUAGES
	{
		AF, AN, AR, AZ, BG, BN, BR, BS, CA, CS, CY, DA, DE, EL, EN, EO, ES, ET, EU, FA, FI, FR, GL, HE, HI, HR, HT, HU, HY, ID, IS, IT, JP, JV, KK, KO, LA, LB, LT, LV, MG, MK, ML, MR, MS, NL, NN, NO, OC, PL, PT, RO, RU, SH, SK, SL, SQ, SR, SV, SW, TA, TE, TH, TL, TR, UK, UR, VI, VO, ZH
	};

	public enum COUNTRIES
	{
		AE, AR, AT, AU, BE, BG, BR, CA, CH, CN, CO, CU, CZ, DE, EG, FR, GB, GR, HK, HU, ID, IE, IL, IN, IT, JP, KR, LT, LV, MA, MX, MY, NG, NL, NO, NZ, PH, PL, PT, RO, RS, RU, SA, SE, SG, SI, SK, TH, TR, TW, UA, US, VE, ZA
	};

	public enum CATEGORIES
	{
		Business, Entertainment, Health, Science, Sports, Technology
	};

	public enum SORT_BYS
	{
		Popularity, PublishedAt, Relevancy
	};

	public enum ERROR_CODES
	{
		ApiKeyExhausted, ApiKeyMissing, ApiKeyInvalid, ApiKeyDisabled, ParametersMissing, ParametersIncompatible, ParameterInvalid, RateLimited, RequestTimeout, SourcesTooMany, SourceDoesNotExist, SourceUnavailableSortedBy, SourceTemporarilyUnavailable, UnexpectedError, UnknownError
	};

	public enum STATUSES
	{
		Ok, Error
	};


}
