**EduLife - Your Personal English Learning Companion**

Welcome to EduLife! Your ultimate app for enhancing your English speaking skills with real-time grammar correction and instant feedback. Below you'll find everything you need to know to get started with EduLife.

**App Screenshots:**

![Screenshot_20240225-090218](https://github.com/samrudha01codespace/EducationalApp/assets/144599345/4ca9bafe-018b-4795-b5f9-0d6711532226)
![Screenshot_20240225-084148](https://github.com/samrudha01codespace/EducationalApp/assets/144599345/6e69c70c-be88-4e52-8d0e-b7ce20442589)

**Video Link** :- https://youtu.be/wlQUOoMpvgE?si=pNxwOIpCidFaZSmr

**App Link** :- 1. https://www.dropbox.com/scl/fi/vb9z4yofn79baejkrtx7v/bolo2.0.apk?rlkey=7zokbdmqm5kdq0fb2exo1xpwn&dl=0
                
                2. https://fastupload.io/LslEEkXZ9hFAqY3/file


## Features:

### 1. Grammar Corrector:
EduLife's Grammar Corrector feature is designed to help you improve your English speaking skills by providing real-time corrections as you speak. Here's how it works:

- **Real-time Correction:** Speak in English, and EduLife will analyze your sentences for grammatical errors on the fly.
- **Correction Suggestions:** Receive instant feedback with corrected versions of your statements, highlighting errors and suggesting improvements.
- **Scoring System:** Get a score out of 10 based on the accuracy and fluency of your spoken English.
- **Progress Tracking:** Monitor your improvement over time with detailed statistics and insights.

### 2. User-friendly Interface:
EduLife boasts a user-friendly interface that makes it easy and intuitive to use. Whether you're a beginner or an advanced English learner, navigating through the app is a breeze.

### 3. Customization Options:
Tailor EduLife to suit your learning preferences with customizable settings. Adjust the difficulty level, choose specific grammar rules to focus on, and personalize your learning experience.

### 4. Learning Resources:
Access a wealth of learning resources within the app, including grammar guides, tutorials, and practice exercises. Expand your knowledge and reinforce your learning with curated content designed to complement your language learning journey.

### 5. Progress Tracking and Performance Analytics:
Track your progress and performance with detailed analytics and reports. Identify areas for improvement, set goals, and monitor your advancement as you work towards English proficiency.

## Getting Started:

1. **Download and Install:**
   - Get started by downloading EduLife from the App Store or Google Play Store.
   - Install the app on your device and follow the on-screen instructions to set up your account.

2. **Enable Microphone Access:**
   - Grant EduLife permission to access your device's microphone to enable real-time speech recognition and correction.

3. **Practice Regularly:**
   - Begin practicing your spoken English with EduLife's Grammar Corrector feature.
   - Speak naturally, and let EduLife provide instant feedback and corrections to help you improve.

4. **Review Feedback and Improve:**
   - Pay attention to the corrections and suggestions provided by EduLife.
   - Use the feedback to learn from your mistakes and enhance your English speaking skills.

5. **Track Your Progress:**
   - Monitor your progress over time using the app's progress tracking features.
   - Set goals, track your scores, and celebrate your achievements as you become more proficient in English.

6. **Explore Additional Resources:**
   - Take advantage of the additional learning resources available within the app.
   - Dive into grammar guides, tutorials, and practice exercises to supplement your learning experience.

7. **Stay Engaged:**
   - Stay engaged with your English learning journey by practicing regularly and exploring new features and content within the app.

## Support and Feedback

If you encounter any issues, have questions, or would like to provide feedback, please don't hesitate to contact our support team at support@edulife.com. We're here to help you make the most of your English learning experience with EduLife.

Start your journey towards English fluency today with EduLife - Your Personal English Learning Companion!

##demo video


##Demo apk Download



## Getting start with code

    (dependencies)
    
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    implementation ("com.google.android.gms:play-services-vision:10.0.0+")

    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")
    implementation ("com.google.mlkit:text-recognition:16.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")



   (IN App>scr>main>com/samrudhasolutions/bolo>utils>constants)

    
    const val BASE_URL="https://api.openai.com/v1/"
    const val CHATGPT_MODEL="gpt-3.5-turbo"
    const val OPENAI_API_KEY="write your api key"  <- write your api key
    var ANSBEFOREEDIT=""
    var ANSWERAFTEREDIT=""
   

   
