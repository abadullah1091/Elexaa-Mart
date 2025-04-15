package com.example.elexaamart.di

import com.example.elexaamart.data.repositories.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth{

        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFireStoreDB(): FirebaseFirestore{

        return  FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebase(jAuth: FirebaseAuth, db:FirebaseFirestore): AuthRepository{

        return AuthRepository(jAuth,db)
    }
}