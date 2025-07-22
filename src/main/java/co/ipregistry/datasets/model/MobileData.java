package co.ipregistry.datasets.model;

import com.maxmind.db.MaxMindDbConstructor;
import com.maxmind.db.MaxMindDbParameter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Represents mobile carrier data extracted from Ipregistry geolocation datasets.
 * Contains carrier identification information including country code, MCC, MNC, and ASN.
 */
@Getter
@EqualsAndHashCode
@ToString
public final class MobileData {

    private final String countryCode;
    private final String mcc;
    private final String mnc;
    private final long asn;

    /**
     * Constructs a new MobileData instance from MaxMind database parameters.
     *
     * @param carrierName the carrier name (used as country code)
     * @param mcc the Mobile Country Code (MCC)
     * @param mnc the Mobile Network Code (MNC)  
     * @param asn the Autonomous System Number (ASN)
     */
    @MaxMindDbConstructor
    public MobileData(
            @MaxMindDbParameter(name = "carrier_name") String carrierName,
            @MaxMindDbParameter(name = "mcc") String mcc,
            @MaxMindDbParameter(name = "mnc") String mnc,
            @MaxMindDbParameter(name = "asn") long asn
    ) {
        if (carrierName != null) {
            this.countryCode = carrierName.intern();
        } else {
            this.countryCode = null;
        }
        if (mcc != null) {
            this.mcc = mcc.intern();
        } else {
            this.mcc = null;
        }
        if (mnc != null) {
            this.mnc = mnc.intern();
        } else {
            this.mnc = null;
        }
        this.asn = asn;

    }

}
