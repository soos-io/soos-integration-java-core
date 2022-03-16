package io.soos.integration;


import com.github.stefanbirkner.systemlambda.SystemLambda;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.jupiter.api.Test;

public class TestAnalysis {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    void setGeneralParams(){
        System.setProperty("m","run_and_wait");
        System.setProperty("of","fail_the_build");
        System.setProperty("armw","50");
        System.setProperty("arpi","5");
        System.setProperty("dte","soos");
        System.setProperty("wd","./");
        System.setProperty("buri","https://dev-api.soos.io/api/");
    }
    @Test
    public void shouldSucceedRust() throws Exception {
       setGeneralParams();
       System.setProperty("scp","./src/test/manifests/rust");
       System.setProperty("pn","java-core-test-rust");
       int status = SystemLambda.catchSystemExit(() -> {
          SOOSApplication.main(new String[]{});
       });

       Assert.assertEquals(0, status);

    }

    @Test
    public void shouldSucceedDart() throws Exception {
        setGeneralParams();
        System.setProperty("scp","./src/test/manifests/dart");
        System.setProperty("pn","java-core-test-dart");
        int status = SystemLambda.catchSystemExit(() -> {
            SOOSApplication.main(new String[]{});
        });

        Assert.assertEquals(0, status);

    }

    @Test
    public void shouldSucceedGemfile() throws Exception {
        setGeneralParams();
        System.setProperty("scp","./src/test/manifests/gemfile");
        System.setProperty("pn","java-core-test-gemfile");
        int status = SystemLambda.catchSystemExit(() -> {
            SOOSApplication.main(new String[]{});
        });

        Assert.assertEquals(0, status);

    }

    @Test
    public void shouldSucceedGradle() throws Exception {
        setGeneralParams();
        System.setProperty("scp","./src/test/manifests/gradle");
        System.setProperty("pn","java-core-test-gradle");
        int status = SystemLambda.catchSystemExit(() -> {
            SOOSApplication.main(new String[]{});
        });

        Assert.assertEquals(0, status);

    }

    @Test
    public void shouldSucceedMaven() throws Exception {
        setGeneralParams();
        System.setProperty("scp","./src/test/manifests/maven");
        System.setProperty("pn","java-core-test-maven");
        int status = SystemLambda.catchSystemExit(() -> {
            SOOSApplication.main(new String[]{});
        });

        Assert.assertEquals(0, status);

    }

    @Test
    public void shouldSuccedNuget() throws Exception {
        setGeneralParams();
        System.setProperty("scp","./src/test/manifests/nuget");
        System.setProperty("pn","java-core-test-nuget");
        int status = SystemLambda.catchSystemExit(() -> {
            SOOSApplication.main(new String[]{});
        });

        Assert.assertEquals(0, status);

    }

    @Test
    public void shouldSuccedComposer() throws Exception {
        setGeneralParams();
        System.setProperty("scp","./src/test/manifests/composer");
        System.setProperty("pn","java-core-test-composer");
        int status = SystemLambda.catchSystemExit(() -> {
            SOOSApplication.main(new String[]{});
        });

        Assert.assertEquals(0, status);

    }
}
