package co.ipregistry.datasets.model;

import com.maxmind.db.MaxMindDbConstructor;
import com.maxmind.db.MaxMindDbParameter;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public final class MobileData {

    private final String countryCode;
    private final String mcc;
    private final String mnc;
    private final long asn;

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
