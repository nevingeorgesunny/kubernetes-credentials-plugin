package org.jenkinsci.plugins.kubernetes.credentials;

import java.util.Arrays;
import java.util.Collection;
import jenkins.security.FIPS140;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.jvnet.hudson.test.FlagRule;

@RunWith(Parameterized.class)
public class UtilsWithoutFIPSTest extends AbstractUtilsFIPSTest {
    @ClassRule
    public static FlagRule<String> fipsFlag = FlagRule.systemProperty(FIPS140.class.getName() + ".COMPLIANCE", "false");

    public UtilsWithoutFIPSTest(String scheme, boolean skipTLSVerify, boolean shouldPass, String motivation) {
        super(scheme, skipTLSVerify, shouldPass, motivation);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
            {"https", true, true, "Not in FIPS mode, any combination should be valid"},
            {"https", false, true, "Not in FIPS mode, any combination should be valid"},
            {"http", true, true, "Not in FIPS mode, any combination should be valid"},
            {"http", false, true, "Not in FIPS mode, any combination should be valid"},
        });
    }
}
