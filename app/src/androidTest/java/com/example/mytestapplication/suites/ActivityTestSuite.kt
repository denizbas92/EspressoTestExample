package com.example.mytestapplication.suites

import com.example.mytestapplication.mitch.MainActivityTest
import com.example.mytestapplication.mitch.SecondaryActivityTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
    MainActivityTest::class,
    SecondaryActivityTest::class
)
class ActivityTestSuite
