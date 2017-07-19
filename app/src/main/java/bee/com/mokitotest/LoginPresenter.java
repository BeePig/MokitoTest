package bee.com.mokitotest;

import rx.Observable;

/**
 * Created by beepi on 19/07/2017.
 */
public class LoginPresenter {
    private static final String MAIL_VALIDATE = "phan.thi.thuy.dung@framgia.com";
    private static final String PASS_VALIDATE = "framgia";

    public LoginPresenter() {
    }

    public boolean onLogin(String mail, String pass) {
        if (mail == null || pass == null) return false;
        if (mail.equals(MAIL_VALIDATE) && pass.equals(PASS_VALIDATE)) return true;
        return false;
    }

    public Observable<User> accessServer(String mail, String pass) {
        if (mail == null || pass == null) return null;
        return Observable.just(new User(mail, pass));
    }
}
