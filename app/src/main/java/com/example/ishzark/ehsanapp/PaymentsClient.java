package com.example.ishzark.ehsanapp;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.wallet.Wallet;

public class PaymentsClient extends GoogleApi<Wallet.WalletOptions> {
    protected PaymentsClient(@NonNull Context context, Api<Wallet.WalletOptions> api, Looper looper) {
        super(context, api, looper);
    }
}
