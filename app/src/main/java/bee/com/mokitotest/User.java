package bee.com.mokitotest;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by beepi on 19/07/2017.
 */
public class User extends BaseObservable {
    private String mMail;
    private String mPassword;

    public User() {
    }

    public User(String mail, String pass) {
        mMail = mail;
        mPassword = pass;
    }

    @Bindable
    public String getMail() {
        return mMail;
    }

    public void setMail(String mail) {
        mMail = mail;
        notifyPropertyChanged(bee.com.mokitotest.BR.mail);
    }

    @Bindable
    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
        notifyPropertyChanged(bee.com.mokitotest.BR.password);
    }
}
