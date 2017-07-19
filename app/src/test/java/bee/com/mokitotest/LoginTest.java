package bee.com.mokitotest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import rx.Observable;
import rx.functions.Action1;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by beepi on 19/07/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginTest {
    @Spy
    LoginPresenter mLoginPresenter;

    @Test
    public void login() {
        boolean isValidate = mLoginPresenter.onLogin("fakeMail", "fakePass");
        /**
         * make true test
         */
        Assert.assertFalse(isValidate);
        /**
         * make false test
         */
        when(mLoginPresenter.onLogin(anyString(),anyString())).thenReturn(false);
        Assert.assertTrue(mLoginPresenter.onLogin("fakeMail", "fakePass"));
    }

    @Test
    public void accessServer(){
        Observable<User> observable = mLoginPresenter.accessServer("fakeMail","fakePass");
        Assert.assertNotNull(observable);
        observable.subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                Assert.assertNotNull(user);
            }
        });
    }
}
