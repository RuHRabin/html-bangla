package com.example.model

data class SectionContent(
    val heading: String,
    val text: String,
    val sampleCode: String? = null,
    val note: String? = null
)

data class LessonTopic(
    val id: String,
    val chapter: String,
    val title: String,
    val subtitle: String,
    val badge: String,
    val summary: String,
    val sections: List<SectionContent>,
    val practiceCode: String,
    val keyBoardTips: List<String>
)

data class QuizQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

object HscLessonsData {

    val lessons: List<LessonTopic> = listOf(
        LessonTopic(
            id = "ch4_1",
            chapter = "অধ্যায় ৪.১",
            title = "ওয়েব ডিজাইন পরিচিতি ও ওয়েবসাইটের প্রকারভেদ",
            subtitle = "ওয়েব পেজ, ওয়েবসাইট, WWW এবং স্ট্যাটিক বনাম ডায়নামিক ওয়েবসাইট",
            badge = "মৌলিক ধারণা",
            summary = "ওয়েব পেজ ও ওয়েবসাইটের সংজ্ঞা, WWW এর ইতিহাস, টিম বার্নার্স-লি এর অবদান এবং স্ট্যাটিক ও ডায়নামিক ওয়েবসাইটের তুলনামূলক বিশ্লেষণ।",
            sections = listOf(
                SectionContent(
                    heading = "ওয়েব পেজ ও ওয়েবসাইট কী?",
                    text = "• ওয়েব পেজ (Web Page): ইন্টারনেটে প্রদর্শন উপযোগী যেকোনো টেক্সট, ছবি, অডিও, ভিডিও সম্বলিত ডিজিটাল তথ্যভাণ্ডারকে ওয়েব পেজ বলে। এটি মূলত HTML ভাষায় তৈরি হয়।\n• ওয়েবসাইট (Website): একই ডোমেইন নেমের অধীনে ইন্টারনেটে সংরক্ষিত পরস্পর সম্পর্কযুক্ত এক বা একাধিক ওয়েব পেজের সমষ্টিকে ওয়েবসাইট বলে।\n• হোম পেজ (Home Page): কোনো ওয়েবসাইটে প্রবেশ করলে প্রথমে যে মূল পেজটি প্রদর্শিত হয়, তাকে হোম পেজ বা ইনডেক্স পেজ (index.html) বলে।",
                    note = "এইচএসসি প্রশ্ন: ওয়েব ডিজাইনের জনক টিম বার্নার্স-লি (Tim Berners-Lee), যিনি ১৯৮৯ সালে WWW উদ্ভাবন করেন।"
                ),
                SectionContent(
                    heading = "স্ট্যাটিক ওয়েবসাইট (Static Website)",
                    text = "• যে সকল ওয়েবসাইটের কন্টেন্ট ওয়েব পেজ লোড করার পর ব্যবহারকারীর ক্রিয়া বা ডাটাবেজ পরিবর্তনের সাথে স্বয়ংক্রিয়ভাবে পরিবর্তিত হয় না, তাকে স্ট্যাটিক ওয়েবসাইট বলে।\n• প্রযুক্তি: HTML, CSS এবং সাধারণ JavaScript দিয়ে তৈরি।\n• সুবিধা: তৈরি করা সহজ, দ্রুত লোড হয়, হোস্টিং খরচ কম ও সাইবার সিকিউরিটি বেশি।\n• অসুবিধা: কন্টেন্ট পরিবর্তন করতে কোড ম্যানুয়ালি এডিট করতে হয়, ব্যবহারকারীর সাথে রিয়েল-টাইম ইন্টারঅ্যাকশন সম্ভব নয়।",
                    note = "বোর্ড প্রশ্ন: শিক্ষাপ্রতিষ্ঠানের নোটিশ বোর্ড বা সাধারণ জীবনবৃত্তান্তের (CV) জন্য স্ট্যাটিক পেজ আদর্শ।"
                ),
                SectionContent(
                    heading = "ডায়নামিক ওয়েবসাইট (Dynamic Website)",
                    text = "• যে সকল ওয়েবসাইটের তথ্য বা কন্টেন্ট ব্যবহারকারীর রিকোয়েস্ট, সার্চ বা ডাটাবেজ পরিবর্তনের সাথে সাথে রিয়েল-টাইমে পরিবর্তিত হয়, তাকে ডায়নামিক ওয়েবসাইট বলে।\n• প্রযুক্তি: পিএইচপি (PHP), পাইথন (Python), নোড জেএস (Node.js), ডেটাবেজ হিসেবে MySQL/Oracle ব্যবহৃত হয়।\n• সুবিধা: সহজেই কন্টেন্ট আপডেট করা যায়, ইউজার লগইন/সাইনআপ ও সার্চ সুবিধা থাকে (যেমন: ফেসবুক, ই-কমার্স সাইট)।\n• অসুবিধা: তৈরি ও রক্ষণাবেক্ষণ ব্যয়বহুল, লোডিং সময় তুলনামূলক বেশি এবং সিকিউরিটি ঝুঁকি বেশি থাকে।"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>অধ্যায় ৪.১ - ওয়েব পরিচিতি</title>
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #f8fafc;">
    <h1 style="color: #0284c7;">ওয়েব ডিজাইন পরিচিতি - HSC ICT</h1>
    <p>ওয়েবসাইট হলো পরস্পর সম্পর্কযুক্ত একাধিক ওয়েব পেজের ডিজিটাল সমন্বয়।</p>
    <hr>
    <h3>স্ট্যাটিক বনাম ডায়নামিক ওয়েবসাইট:</h3>
    <table border="1" cellpadding="6" style="border-collapse: collapse; width: 100%; background: white;">
        <tr bgcolor="#e0f2fe">
            <th>বৈশিষ্ট্য</th>
            <th>স্ট্যাটিক ওয়েবসাইট</th>
            <th>ডায়নামিক ওয়েবসাইট</th>
        </tr>
        <tr>
            <td>কন্টেন্ট পরিবর্তন</td>
            <td>নির্দিষ্ট, পরিবর্তন হয় না</td>
            <td>রিয়েল-টাইমে পরিবর্তিত হয়</td>
        </tr>
        <tr>
            <td>ডাটাবেজ</td>
            <td>ডাটাবেজ থাকে না</td>
            <td>ডাটাবেজ অপরিহার্য</td>
        </tr>
        <tr>
            <td>গতি</td>
            <td>অত্যন্ত দ্রুত</td>
            <td>ডাটাবেজ কোয়েরির কারণে ধীর</td>
        </tr>
    </table>
    <p style="background: #e0f2fe; padding: 10px; border-radius: 6px; margin-top: 12px;">
        💡 <b>এইচএসসি টিপ:</b> স্ট্যাটিক পেজে HTML/CSS ব্যবহার হয়, কিন্তু ডায়নামিক পেজে সার্ভার-সাইড ভাষা ও ডাটাবেজ লাগে!
    </p>
</body>
</html>""",
            keyBoardTips = listOf(
                "টিম বার্নার্স-লি ১৯৮৯ সালে WWW এবং ১৯৯০ সালে প্রথম HTML ব্রাউজার তৈরি করেন।",
                "স্ট্যাটিক ওয়েবসাইটে ডেটাবেজ যুক্ত থাকে না, ডায়নামিক ওয়েবসাইটে ডেটাবেজ থাকে।",
                "ওয়েবসাইটের মূল প্রবেশদ্বারকে Home Page বলা হয়।"
            )
        ),

        LessonTopic(
            id = "ch4_2",
            chapter = "অধ্যায় ৪.২",
            title = "আইপি অ্যাড্রেস, ডোমেন নেম ও ডিএনএস",
            subtitle = "IPv4 বনাম IPv6, টপ-লেভেল ডোমেন (gTLD, ccTLD) ও DNS এর ভূমিকা",
            badge = "নেটওয়ার্কিং ভিত্তি",
            summary = "আইপি অ্যাড্রেসের গঠন, IPv4 ও IPv6 এর পার্থক্য, ডোমেন নেম সিস্টেম (DNS) এবং বিভিন্ন ধরনের ডোমেন এক্সটেনশন।",
            sections = listOf(
                SectionContent(
                    heading = "আইপি অ্যাড্রেস (IP Address)",
                    text = "ইন্টারনেটের সাথে যুক্ত প্রতিটি কম্পিউটার বা ডিভাইসের একটি অনন্য (Unique) ডিজিটাল সংখ্যাগত ঠিকানা থাকে, যাকে আইপি অ্যাড্রেস বলে।\n• IPv4 (Internet Protocol version 4): এটি ৩২ বিটের ঠিকানা। ৪টি অক্টেট (প্রতিটিতে ৮ বিট) ডট (.) দ্বারা পৃথক থাকে। উদাহরণ: 192.168.1.1। মোট সম্ভাব্য ঠিকানা ২^৩২ টি (প্রায় ৪.৩ বিলিয়ন)।\n• IPv6 (Internet Protocol version 6): এটি ১২৮ বিটের ঠিকানা। ৮টি হেক্সাডেসিমেল গ্রুপ কোলন (:) দ্বারা পৃথক থাকে। উদাহরণ: 2001:0db8:85a3::8a2e:0370:7334।",
                    note = "বোর্ড প্রশ্ন: IPv4 কত বিটের? উত্তর: ৩২ বিট। IPv6 কত বিটের? উত্তর: ১২৮ বিট।"
                ),
                SectionContent(
                    heading = "ডোমেন নেম ও ডিএনএস (Domain Name & DNS)",
                    text = "• ডোমেন নেম: আইপি অ্যাড্রেসের গাণিতিক সংখ্যা মনে রাখা মানুষের জন্য কঠিন বিধায় এর পরিবর্তে সহজে মনে রাখার জন্য যে আলফা-নিউমেরিক নাম ব্যবহার করা হয়, তাকে ডোমেন নেম বলে (যেমন: www.google.com)।\n• ডোমেন নেম নিয়ন্ত্রক সংস্থা: ICANN (Internet Corporation for Assigned Names and Numbers)।\n• DNS (Domain Name System): ইন্টারনেটের ফোনবুকের মতো কাজ করে। এটি ডোমেন নেমকে আইপি অ্যাড্রেসে এবং আইপি অ্যাড্রেসকে ডোমেন নেমে রূপান্তর করে।"
                ),
                SectionContent(
                    heading = "টপ লেভেল ডোমেন (TLD - Top Level Domain)",
                    text = "১. জেনেরিক টিএলডি (gTLD):\n• .com - বাণিজ্যিক প্রতিষ্ঠান (Commercial)\n• .edu - শিক্ষা প্রতিষ্ঠান (Educational)\n• .gov - সরকারি সংস্থা (Government)\n• .org - অলাভজনক সংস্থা (Organization)\n• .mil - সামরিক সংস্থা (Military)\n• .net - নেটওয়ার্কিং সেবা (Networking)\n২. কান্ট্রি কোড টিএলডি (ccTLD):\n• .bd - বাংলাদেশ, .in - ভারত, .uk - যুক্তরাজ্য, .us - যুক্তরাষ্ট্র।"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>আইপি ও ডোমেন ডেমো</title>
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #fffbeb;">
    <h2 style="color: #b45309;">আইপি অ্যাড্রেস ও ডোমেন নেম</h2>
    <p>ব্রাউজার ডোমেন নেমকে DNS সার্ভারের মাধ্যমে আইপি অ্যাড্রেসে রূপান্তর করে ওয়েব সার্ভারে পৌঁছায়।</p>
    
    <div style="background: white; border: 1px solid #fcd34d; border-radius: 8px; padding: 14px;">
        <h4>আইপি অ্যাড্রেস তুলনা:</h4>
        <p>• <b>IPv4:</b> <code>192.168.0.1</code> (৩২ বিট, ৪টি অক্টেট)</p>
        <p>• <b>IPv6:</b> <code>2001:0db8:85a3::7334</code> (১২৮ বিট, ৮টি গ্রুপ)</p>
    </div>

    <div style="margin-top: 12px; background: #fef3c7; padding: 10px; border-radius: 6px;">
        📌 <b>মনে রাখুন:</b> .edu ডোমেন কেবল শিক্ষা প্রতিষ্ঠান এবং .gov ডোমেন কেবল সরকার ব্যবহার করতে পারে।
    </div>
</body>
</html>""",
            keyBoardTips = listOf(
                "IPv4 ৩২ বিটের এবং IPv6 ১২৮ বিটের।",
                "DNS এর পূর্ণরূপ Domain Name System যা নামকে আইপিতে রূপান্তর করে।",
                "ডোমেন নেম বরাদ্দের দায়িত্ব ICANN নামক আন্তর্জাতিক সংস্থার।"
            )
        ),

        LessonTopic(
            id = "ch4_3",
            chapter = "অধ্যায় ৪.৩",
            title = "ইউআরএল (URL) ও ওয়েব ব্রাউজিং আর্কিটেকচার",
            subtitle = "প্রটোকল, ডোমেন, পোর্ট, পাথ এবং ক্লায়েন্ট-সার্ভার মডেল",
            badge = "ইউআরএল গঠন",
            summary = "URL এর পূর্ণরূপ ও প্রতিটি অংশের বিশদ বিশ্লেষণ, ওয়েব ক্লায়েন্ট (ব্রাউজার) ও ওয়েব সার্ভারের মধ্যকার যোগাযোগ প্রক্রিয়া।",
            sections = listOf(
                SectionContent(
                    heading = "ইউআরএল (URL) কী ও এর গঠন",
                    text = "URL এর পূর্ণরূপ হলো Uniform Resource Locator। এটি ইন্টারনেটে কোনো ফাইলের পূর্ণাঙ্গ নির্দিষ্ট ঠিকানা নির্দেশ করে।\n\nএকটি আদর্শ URL-এর উদাহরণ:\nhttps://www.moedu.gov.bd:80/notice/hsc_routine.html?year=2026\n\nএর প্রধান অংশগুলো হলো:\n১. প্রটোকল (Protocol): https:// (HyperText Transfer Protocol Secure - এনক্রিপ্টেড ডাটা আদান-প্রদান)।\n২. ডোমেন নেম / হোস্ট (Domain/Host): www.moedu.gov.bd (সার্ভারের নাম)।\n৩. পোর্ট নম্বর (Port): :80 বা :443 (সাধারণত ডিফল্ট হিসেবে লুকানো থাকে)।\n৪. ডিরেক্টরি পাথ (Directory Path): /notice/ (সার্ভারে ফোল্ডারের অবস্থান)।\n৫. ফাইল নেইম (File Name): hsc_routine.html (নির্দিষ্ট ফাইলটির নাম)।\n৬. কুয়েরি স্ট্রিং (Query String): ?year=2026 (সার্চ বা ফিল্টারিং প্যারামিটার)।",
                    note = "বোর্ড পরীক্ষায় প্রায়ই একটি URL দিয়ে প্রটোকল, ডোমেন বা ফাইলের নাম চিহ্নিত করতে বলে।"
                ),
                SectionContent(
                    heading = "ওয়েব ক্লায়েন্ট ও ওয়েব সার্ভার (Client-Server Model)",
                    text = "• ওয়েব ব্রাউজার / ক্লায়েন্ট: যে সফটওয়্যার ব্যবহার করে ইন্টারনেটে ওয়েব পেজ দেখা ও রিকোয়েস্ট পাঠানো হয় (যেমন: Chrome, Firefox, Safari)।\n• ওয়েব সার্ভার: ইন্টারনেটের সাথে যুক্ত যে শক্তিশালী কম্পিউটারে ওয়েবসাইটের সমস্ত ফাইল জমা থাকে এবং ব্রাউজারের রিকোয়েস্টে ফাইল ব্রাউজারে পাঠিয়ে দেয় (যেমন: Apache, Nginx, IIS)।\n• প্রটোকল: ক্লায়েন্ট ও সার্ভারের মধ্যে তথ্য আদান-প্রদানের নিয়ম (HTTP/HTTPS)।"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>URL অ্যানাটমি ডেমো</title>
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #fdf4ff;">
    <h2 style="color: #701a75;">URL এর বিভিন্ন অংশের বিশ্লেষণ</h2>
    
    <div style="background: white; border: 2px solid #f0abfc; padding: 12px; border-radius: 8px; font-family: monospace; font-size: 14px;">
        <span style="color: #c026d3; font-weight: bold;">https://</span><span style="color: #2563eb; font-weight: bold;">www.dhakaeducationboard.gov.bd</span><span style="color: #16a34a; font-weight: bold;">/hsc/</span><span style="color: #d97706; font-weight: bold;">result.html</span>
    </div>

    <ul style="line-height: 1.8; margin-top: 14px;">
        <li><span style="color: #c026d3; font-weight: bold;">https://</span> = প্রটোকল (Protocol)</li>
        <li><span style="color: #2563eb; font-weight: bold;">www.dhakaeducationboard.gov.bd</span> = ডোমেন নেম (Domain Name)</li>
        <li><span style="color: #16a34a; font-weight: bold;">/hsc/</span> = ডিরেক্টরি পাথ (Directory Path)</li>
        <li><span style="color: #d97706; font-weight: bold;">result.html</span> = এইচটিএমএল ফাইল নেইম (File Name)</li>
    </ul>
</body>
</html>""",
            keyBoardTips = listOf(
                "URL এর পূর্ণরূপ Uniform Resource Locator।",
                "HTTPS-এর 'S' দিয়ে Secure বোঝায়, যা SSL/TLS এনক্রিপশন ব্যবহার করে।",
                "ব্রাউজার হলো ওয়েব ক্লায়েন্ট এবং ডেটা হোস্ট করা কম্পিউটার হলো ওয়েব সার্ভার।"
            )
        ),

        LessonTopic(
            id = "ch4_4",
            chapter = "অধ্যায় ৪.৪",
            title = "ওয়েবসাইটের কাঠামো (Website Structures)",
            subtitle = "লিনিয়ার, ট্রি/হায়ারার্কিক্যাল, নেটওয়ার্ক এবং হাইব্রিড কাঠামোর তুলনামূলক বিশ্লেষণ",
            badge = "কাঠামো স্পেশাল",
            summary = "ওয়েবসাইটের ৪টি প্রধান কাঠামোর বৈশিষ্ট্য, বাস্তব উদাহরণ, এবং সৃজনশীল পরীক্ষায় চিত্রভিত্তিক প্রশ্নের সঠিক শনাক্তকরণ।",
            sections = listOf(
                SectionContent(
                    heading = "ওয়েবসাইটের কাঠামোর মূল ৪টি রূপ",
                    text = "একটি ওয়েবসাইটের পেজগুলো কীভাবে একে অপরের সাথে সাজানো বা সংযুক্ত থাকে, তাকে ওয়েবসাইটের কাঠামো (Website Structure) বলে।\n\n১. লিনিয়ার বা অনুক্রমিক (Linear Structure):\n• পেজগুলো একটি নির্দিষ্ট ধারায় (১ -> ২ -> ৩ -> ৪) ব্রাউজ করতে হয়।\n• এতে Next, Previous, First, Last বাটন থাকে।\n• ব্যবহার: অনলাইন বই, প্রশিক্ষণ ম্যানুয়াল, ধাপে ধাপে রেজিস্ট্রেশন বা পরীক্ষার ফলাফল দেখার জন্য উপযুক্ত।\n\n২. হায়ারার্কিক্যাল বা ট্রি (Tree / Hierarchical Structure):\n• সবচেয়ে জনপ্রিয় ও বহুল ব্যবহৃত কাঠামো।\n• এতে একটি মূল হোম পেজ থাকে এবং তার অধীনে বিভিন্ন মেনু ও সাবমেনু শাখার মতো বিস্তৃত থাকে।\n• ব্যবহার: স্কুল, কলেজ, বিশ্ববিদ্যালয় ও প্রাতিষ্ঠানিক ওয়েবসাইটে ব্যবহৃত হয়।\n\n৩. নেটওয়ার্ক বা ওয়েব-লিঙ্কড (Network / Web-linked Structure):\n• প্রতিটি পেজ সরাসরি অন্য যেকোনো পেজের সাথে সংযুক্ত থাকে (Mesh কানেকশন)।\n• ব্যবহার: সামাজিক যোগাযোগ মাধ্যম ও উইকিপিডিয়া।\n\n৪. কম্বিনেশন বা হাইব্রিড (Combination / Hybrid Structure):\n• লিনিয়ার, ট্রি ও নেটওয়ার্ক কাঠামোর সমন্বয়ে জটিল বৃহৎ ওয়েবসাইট তৈরি করা হয়।"
                ),
                SectionContent(
                    heading = "বোর্ড পরীক্ষায় উদ্দীপক সমাধান টিপস",
                    text = "• যদি উদ্দীপকে পেজগুলো একটার পর একটা তীরচিহ্ন দিয়ে পর্যায়ক্রমিক দেখানো থাকে -> সেটি লিনিয়ার কাঠামো।\n• যদি হোম পেজ থেকে বিভিন্ন বিভাগের শাখা বের হয় -> সেটি ট্রি বা হায়ারার্কিক্যাল কাঠামো।\n• যদি সব পেজ একে অপরের সাথে দ্বিমুখী তীর দিয়ে যুক্ত থাকে -> সেটি নেটওয়ার্ক কাঠামো।"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>ওয়েবসাইট কাঠামো সিমুলেশন</title>
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #f1f5f9;">
    <h2 style="color: #0f172a;">ট্রি বা হায়ারার্কিক্যাল কাঠামো (কলেজ ওয়েবসাইট)</h2>
    
    <div style="background: white; padding: 14px; border-radius: 8px; border: 1px solid #cbd5e1;">
        <div style="background: #0284c7; color: white; padding: 8px; text-align: center; border-radius: 4px; font-weight: bold;">
            হোম পেজ (Home Page)
        </div>
        <div style="text-align: center; font-size: 18px; color: #0284c7;">↓</div>
        <div style="display: flex; gap: 8px; justify-content: space-around;">
            <div style="background: #e0f2fe; padding: 8px; border-radius: 4px; font-size: 13px; text-align: center; flex: 1;">
                ভর্তি তথ্য
            </div>
            <div style="background: #e0f2fe; padding: 8px; border-radius: 4px; font-size: 13px; text-align: center; flex: 1;">
                শিক্ষক পরিষদ
            </div>
            <div style="background: #e0f2fe; padding: 8px; border-radius: 4px; font-size: 13px; text-align: center; flex: 1;">
                পরীক্ষার ফলাফল
            </div>
        </div>
    </div>
</body>
</html>""",
            keyBoardTips = listOf(
                "স্কুল-কলেজের ওয়েবসাইটে ট্রি বা হায়ারার্কিক্যাল কাঠামো সবচেয়ে বেশি ব্যবহৃত হয়।",
                "অনলাইন টিউটোরিয়াল ও ধারাবাহিক ট্রেইনিংয়ে লিনিয়ার কাঠামো প্রযোজ্য।",
                "হাইব্রিড হলো একাধিক কাঠামোর সমন্বিত রূপ।"
            )
        ),

        LessonTopic(
            id = "ch4_5",
            chapter = "অধ্যায় ৪.৫",
            title = "এইচটিএমএল-এর পরিচিতি ও মৌলিক গঠন",
            subtitle = "ট্যাগ, অ্যাট্রিবিউট, কনটেইনার ও এম্পটি ট্যাগ, বেসিক সিনট্যাক্স",
            badge = "এইচটিএমএল ভিত্তি",
            summary = "HTML5 ডকুমেন্টের মৌলিক গঠন, ওপেনিং ও ক্লোজিং ট্যাগ, এম্পটি ট্যাগ এবং অ্যাট্রিবিউটের নিয়মাবলী।",
            sections = listOf(
                SectionContent(
                    heading = "HTML পরিচিতি ও বৈশিষ্ট্য",
                    text = "HTML এর পূর্ণরূপ HyperText Markup Language। এটি কোনো প্রোগ্রামিং ল্যাঙ্গুয়েজ নয়, এটি একটি স্ক্রিপ্টিং বা মার্কআপ ভাষা।\n• কেস-ইনসেনসিটিভ: ছোট হাত (lowercase) বা বড় হাত (UPPERCASE) উভয় অক্ষরেই কাজ করে (তবে W3C ছোট হাতের অক্ষর লেখার সুপারিশ করে)।\n• সাধারণ টেক্সট এডিটরে (যেমন Notepad) কোড লিখে .html বা .htm এক্সটেনশন দিয়ে সংরক্ষণ করা হয়।"
                ),
                SectionContent(
                    heading = "ট্যাগ, এলিমেন্ট ও অ্যাট্রিবিউট",
                    text = "• ট্যাগ (Tag): আ্যঙ্গেল ব্র্যাকেট (< >) দ্বারা আবদ্ধ বিশেষ কীওয়ার্ড। যেমন: <p>, <b>।\n• কনটেইনার ট্যাগ (Container Tag): যার শুরু ট্যাগ (<tag>) ও শেষ ট্যাগ (</tag>) থাকে এবং মাঝে কন্টেন্ট থাকে। যেমন: <h1>শিরোনাম</h1>।\n• এম্পটি ট্যাগ (Empty Tag): যার শেষ ট্যাগ বা কন্টেন্ট থাকে না। যেমন: <br>, <hr>, <img>, <input>, <meta>।\n• অ্যাট্রিবিউট (Attribute): ট্যাগের আচরণ বা বৈশিষ্ট্য নির্ধারণ করে। এটি সর্বদা ওপেনিং ট্যাগে বসে। যেমন: <table border=\"1\"> এ border হলো অ্যাট্রিবিউট এবং 1 হলো মান (Value)।",
                    note = "এইচএসসি প্রশ্ন: নিচের কোনটি এম্পটি ট্যাগ? বোর্ড পরীক্ষায় প্রায়ই আসে।"
                ),
                SectionContent(
                    heading = "HTML5 স্ট্যান্ডার্ড কাঠামো",
                    text = "একটি বৈধ HTML5 পেজের মূল অংশ:\n১. <!DOCTYPE html> : ব্রাউজারকে এটি HTML5 পেজ বলে জানায়।\n২. <html> : সম্পূর্ণ ডকুমেন্টের রুট কনটেইনার।\n৩. <head> : পেজের মেটাডেটা, টাইটেল ও সিএসএস লিংকের অংশ।\n৪. <body> : পেজের দৃশ্যমান সমস্ত তথ্য প্রদর্শনের অংশ।"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>এইচটিএমএল মৌলিক গঠন</title>
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #fefce8;">
    <h2 style="color: #a16207;">এইচটিএমএল এর বেসিক ট্যাগ</h2>
    <p>এটি একটি কনটেইনার প্যারাগ্রাফ ট্যাগ।</p>
    <hr>
    <p>নিচে একটি লাইন ব্রেক (Empty Tag):<br>
    লাইন ব্রেকের কারণে এই লেখাটি নিচের লাইনে চলে এসেছে।</p>
</body>
</html>""",
            keyBoardTips = listOf(
                "<br>, <hr>, <img>, <input>, <meta> হলো এম্পটি ট্যাগ।",
                "অ্যাট্রিবিউট সবসময় ওপেনিং ট্যাগের ভেতর বসে।",
                "<!DOCTYPE html> দিয়ে HTML5 সংস্করণ নির্দেশ করা হয়।"
            )
        ),

        LessonTopic(
            id = "ch4_6",
            chapter = "অধ্যায় ৪.৬",
            title = "টেক্সট ফরম্যাটিং, হেডিং ও অনুচ্ছেদ",
            subtitle = "h1-h6, b, i, u, sub, sup, del, mark, strong, em এবং স্পেশাল ক্যারেক্টার",
            badge = "টেক্সট ডিজাইন",
            summary = "হেডিং এর ৬টি স্তর, প্যারাগ্রাফ, বোল্ড, ইটালিক, আন্ডারলাইন এবং বৈজ্ঞানিক সংকেতের জন্য সাবস্ক্রিপ্ট ও সুপারস্ক্রিপ্ট।",
            sections = listOf(
                SectionContent(
                    heading = "হেডিং ট্যাগ (<h1> থেকে <h6>)",
                    text = "এইচটিএমএল-এ মোট ৬ ধরনের হেডিং ট্যাগ রয়েছে:\n• <h1> : সবচেয়ে বড় মাপের হেডিং।\n• <h6> : সবচেয়ে ছোট মাপের হেডিং।\n(পরীক্ষায় আসে: সবচেয়ে বড় হেডিং কোনটি? উত্তর: <h1>। সবচেয়ে ছোট কোনটি? উত্তর: <h6>)"
                ),
                SectionContent(
                    heading = "এইচএসসি স্পেশাল ফরম্যাটিং ট্যাগ",
                    text = "• <b> বা <strong> : লেখা গাঢ় বা বোল্ড করতে।\n• <i> বা <em> : লেখা বাঁকা বা ইটালিক করতে।\n• <u> বা <ins> : লেখার নিচে দাগ দিতে (Underline)।\n• <sup> (Superscript) : পাওয়ার বা ঘাত হিসেবে লিখতে। যেমন: (a+b)<sup>2</sup>।\n• <sub> (Subscript) : কোনো অক্ষরের পাদদেশে লিখতে। যেমন: H<sub>2</sub>O।\n• <del> বা <s> : লেখার ওপর কাটা দাগ (Strikethrough) দিতে।\n• <mark> : লেখা হাইলাইট করতে।"
                ),
                SectionContent(
                    heading = "এইচটিএমএল স্পেশাল এন্টিটি (Entities)",
                    text = "• &lt; (< এর জন্য)\n• &gt; (> এর জন্য)\n• &amp; (& এর জন্য)\n• &nbsp; (একটি ফাঁকা স্পেস দেওয়ার জন্য)\n• &copy; (কপিরাইট © চিহ্নের জন্য)"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>টেক্সট ফরম্যাটিং ডেমো</title>
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #fdf2f8;">
    <h1 style="color: #9d174d;">এইচএসসি আইসিটি ফরম্যাটিং</h1>
    <h6>এটি সবচেয়ে ছোট h6 হেডিং</h6>
    <hr>
    <p><b>বোল্ড টেক্সট</b>, <i>ইটালিক টেক্সট</i> ও <u>আন্ডারলাইন টেক্সট</u></p>
    <p>বীজগণিত: (x + y)<sup>2</sup> = x<sup>2</sup> + 2xy + y<sup>2</sup></p>
    <p>রসায়ন: H<sub>2</sub>SO<sub>4</sub> (সালফিউরিক এসিড)</p>
    <p>পূর্বের ফি: <del>৳১০০০</del> বর্তমান ফি: <mark>৳৬০০</mark></p>
    <p>কপিরাইট চিহ্ন: &copy; ২০২৬ এইচএসসি আইসিটি</p>
</body>
</html>""",
            keyBoardTips = listOf(
                "H2O এর জন্য <sub> এবং (a+b)2 এর জন্য <sup> ট্যাগ প্রয়োজন।",
                "<h1> সবচেয়ে বড় এবং <h6> সবচেয়ে ছোট হেডিং।",
                "একাধিক স্পেস দিতে &nbsp; এন্টিটি ব্যবহৃত হয়।"
            )
        ),

        LessonTopic(
            id = "ch4_7",
            chapter = "অধ্যায় ৪.৭",
            title = "এইচটিএমএল লিস্ট: ক্রম ও বুলেট",
            subtitle = "Ordered (<ol>), Unordered (<ul>) ও Definition (<dl>) লিস্ট",
            badge = "লিস্ট মেকিং",
            summary = "তথ্য সুশৃঙ্খলভাবে উপস্থাপন করতে ক্রমানুসারী সংখ্যা, রোমান সংখ্যা, বুলেট ও বর্ণমালার তালিকা তৈরি।",
            sections = listOf(
                SectionContent(
                    heading = "অর্ডার্ড লিস্ট (<ol> - Ordered List)",
                    text = "যে তালিকায় উপাদানগুলোর নির্দিষ্ট ক্রম বা নম্বর থাকে। প্রতিটি আইটেম <li> দিয়ে শুরু হয়।\n• type অ্যাট্রিবিউট:\n  - type=\"1\" : ডিফল্ট সংখ্যা (1, 2, 3...)\n  - type=\"A\" : বড় হাতের ইংরেজি অক্ষর (A, B, C...)\n  - type=\"a\" : ছোট হাতের ইংরেজি অক্ষর (a, b, c...)\n  - type=\"I\" : বড় হাতের রোমান সংখ্যা (I, II, III...)\n  - type=\"i\" : ছোট হাতের রোমান সংখ্যা (i, ii, iii...)\n• start অ্যাট্রিবিউট: তালিকা কত নম্বর থেকে শুরু হবে। যেমন: <ol type=\"1\" start=\"5\"> দিলে ৫ থেকে শুরু হবে।"
                ),
                SectionContent(
                    heading = "আন-অর্ডার্ড লিস্ট (<ul> - Unordered List)",
                    text = "যে তালিকায় নম্বরের পরিবর্তে বুলেট পয়েন্ট ব্যবহার করা হয়।\n• type অ্যাট্রিবিউট:\n  - disc : কালো ভরাট গোলক (ডিফল্ট)\n  - circle : ফাঁপা বৃত্তাকার বৃত্ত\n  - square : ভরাট চারকোনা বর্গক্ষেত্র\n  - none : কোনো বুলেট চিহ্ন থাকবে না"
                ),
                SectionContent(
                    heading = "ডেফিনিশন লিস্ট (<dl>)",
                    text = "সংজ্ঞা ও তার বিবরণ প্রদর্শনে ব্যবহৃত হয়।\n• <dl> : Definition List\n• <dt> : Definition Term (শব্দ)\n• <dd> : Definition Description (বিবরণ)"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>লিস্ট অনুশীলন</title>
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #f8fafc;">
    <h3 style="color: #1e3a8a;">এইচএসসি আইসিটি বইয়ের অধ্যায়সমূহ</h3>
    
    <ol type="I" start="1">
        <li>তথ্য ও যোগাযোগ প্রযুক্তি: বিশ্ব ও বাংলাদেশ প্রেক্ষিত</li>
        <li>কমিউনিকেশন সিস্টেমস ও নেটওয়ার্কিং</li>
        <li>সংখ্যা পদ্ধতি ও ডিজিটাল ডিভাইস</li>
        <li>ওয়েব ডিজাইন পরিচিতি এবং HTML</li>
        <li>প্রোগ্রামিং ভাষা (C ভাষা)</li>
    </ol>

    <hr>
    <h4>আন-অর্ডার্ড লিস্ট (Square বুলেট):</h4>
    <ul type="square">
        <li>বাংলা প্রথম পত্র</li>
        <li>ইংরেজি দ্বিতীয় পত্র</li>
        <li>তথ্য ও যোগাযোগ প্রযুক্তি</li>
    </ul>
</body>
</html>""",
            keyBoardTips = listOf(
                "<ol> এর ডিফল্ট টাইপ 1 এবং <ul> এর ডিফল্ট টাইপ disc।",
                "start=\"3\" দিলে লিস্ট ৩ নম্বর থেকে শুরু হয়।",
                "নেস্টেড লিস্টের ক্ষেত্রে একটি <li> এর ভেতর আরেকটি <ol> বা <ul> বসে।"
            )
        ),

        LessonTopic(
            id = "ch4_8",
            chapter = "অধ্যায় ৪.৮",
            title = "হাইপারলিংক সংযোজন (Hyperlink)",
            subtitle = "<a> ট্যাগ, href, target=\"_blank\", ইন্টারনাল, এক্সটারনাল ও বুকমার্ক লিংক",
            badge = "লিংক ডিজাইন",
            summary = "একটি ওয়েব পেজের সাথে অন্য পেজ, সাইট বা ফাইলের সংযোগ স্থাপন এবং বিভিন্ন ধরণের লিংকের কৌশল।",
            sections = listOf(
                SectionContent(
                    heading = "হাইপারলিংক ও <a> ট্যাগ",
                    text = "একটি ওয়েব পেজের কোনো টেক্সট বা ছবিতে ক্লিক করে অন্য পেজে যাওয়ার পদ্ধতিকে হাইপারলিংক বলে। এর জন্য <a> (Anchor) ট্যাগ ব্যবহার করা হয়।\n• মৌলিক রূপ: <a href=\"URL\">ক্লিক করুন</a>\n• href (Hypertext Reference): যে ঠিকানায় যেতে হবে তার লিংক।"
                ),
                SectionContent(
                    heading = "লিংকের প্রকারভেদ (Internal vs External)",
                    text = "১. ইন্টারনাল লিংক (Internal / Relative Link):\nএকই ওয়েবসাইটের এক পেজ থেকে অন্য পেজের লিংক। যেমন: <a href=\"about.html\">আমাদের সম্পর্কে</a>।\n\n২. এক্সটারনাল লিংক (External / Absolute Link):\nসম্পূর্ণ প্রটোকলসহ অন্য ওয়েবসাইটের ঠিকানা। যেমন: <a href=\"https://www.google.com\">গুগল</a>।\n\n৩. বুকমার্ক লিংক (Bookmark Link):\nএকই পেজের নির্দিষ্ট প্যারাগ্রাফে জাম্প করতে। যেমন: <a href=\"#contact\">যোগাযোগ অংশে যান</a>।\n\n৪. ইমেইল লিংক: <a href=\"mailto:info@college.edu.bd\">ইমেইল পাঠান</a>।"
                ),
                SectionContent(
                    heading = "target অ্যাট্রিবিউট",
                    text = "• target=\"_blank\": লিংকটি নতুন ট্যাব বা উইন্ডোতে ওপেন হয়।\n• target=\"_self\": একই ট্যাবে পেজটি লোড হয় (ডিফল্ট)।"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>হাইপারলিংক ডেমো</title>
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #f0fdf4;">
    <h2 style="color: #166534;">হাইপারলিংক সংযোগ</h2>
    <p>এক্সটারনাল লিংক: <a href="https://bangladesh.gov.bd" target="_blank" style="color: #15803d; font-weight: bold;">বাংলাদেশ জাতীয় তথ্য বাতায়ন (নতুন ট্যাবে)</a></p>
    <hr>
    <p>ইন্টারনাল লিংক ডেমো: <a href="admission.html" style="color: #0284c7;">ভর্তি তথ্য পেজ</a></p>
    <p>ইমেইল লিংক: <a href="mailto:hsc@ict.edu.bd" style="color: #b45309;">ict@education.gov.bd</a></p>
</body>
</html>""",
            keyBoardTips = listOf(
                "target=\"_blank\" লিঙ্ককে নতুন উইন্ডোতে ওপেন করে।",
                "ইমেইল লিংকের জন্য href=\"mailto:...\" ব্যবহৃত হয়।",
                "<a> একটি কনটেইনার ট্যাগ যার ক্লোজিং </a> আবশ্যক।"
            )
        ),

        LessonTopic(
            id = "ch4_9",
            chapter = "অধ্যায় ৪.৯",
            title = "ইমেজ ও মাল্টিমিডিয়া উপাদান",
            subtitle = "<img> ট্যাগ, src, alt, width, height, <audio>, <video> ও <iframe>",
            badge = "মাল্টিমিডিয়া",
            summary = "ওয়েব পেজে ছবি প্রদর্শন, ছবির সাইজ ও বিকল্প টেক্সট দেওয়া এবং অডিও, ভিডিও ও আইফ্রেম সংযোজন।",
            sections = listOf(
                SectionContent(
                    heading = "ইমেজ যুক্তকরণ (<img> ট্যাগ)",
                    text = "ছবি যুক্ত করতে <img> (Empty Tag) ব্যবহার করা হয়।\n• src (Source): ছবির ফাইল বা ইন্টারনেট ইউআরএল (যেমন: src=\"pic.jpg\")।\n• alt (Alternate Text): ছবি লোড না হলে যে বিকল্প লেখা প্রদর্শিত হবে।\n• width ও height: ছবির প্রস্থ ও উচ্চতা নির্ধারণ করে (পিক্সেল বা %)।"
                ),
                SectionContent(
                    heading = "অডিও, ভিডিও ও আইফ্রেম",
                    text = "• <audio controls>: ওয়েব পেজে অডিও প্লেয়ার যুক্ত করতে। controls অ্যাট্রিবিউট না দিলে প্লে বাটন দেখা যাবে না।\n• <video controls width=\"320\" height=\"240\">: ভিডিও প্লেয়ার প্রদর্শনে।\n• <iframe> (Inline Frame): পেজের ভেতর অন্য পেজ বা ইউটিউব ভিডিও এম্বেড করতে ব্যবহার হয়। যেমন: <iframe src=\"page.html\" width=\"300\" height=\"200\"></iframe>।"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>ইমেজ ও মাল্টিমিডিয়া</title>
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #faf5ff;">
    <h2 style="color: #6b21a8;">ইমেজ ও মাল্টিমিডিয়া উপাদান</h2>
    <div style="text-align: center; background: white; padding: 16px; border-radius: 8px; border: 1px dashed #a855f7;">
        <svg width="80" height="80" viewBox="0 0 512 512">
            <polygon fill="#E44D26" points="107.6,471 74.6,90.2 437.4,90.2 404.4,470.8 255.8,512"/>
            <polygon fill="#F16529" points="256,480.5 376.4,447.1 404.2,135.2 256,135.2"/>
            <polygon fill="#EBEBEB" points="256,268.2 201.5,268.2 197.7,225.8 256,225.8 256,183.4 151.7,183.4 163,310.6 256,310.6"/>
            <polygon fill="#FFFFFF" points="256,380.9 255.8,380.9 208.6,368.2 205.6,334.6 163,334.6 169,401.7 255.8,425.8 256,425.7"/>
        </svg>
        <p>HTML5 ভেক্টর প্রতীক (অফলাইনে সরাসরি প্রদর্শিত)</p>
    </div>
    <hr>
    <p><b>অডিও প্লেয়ার কোড:</b></p>
    <audio controls style="width: 100%;">
        <source src="audio.mp3" type="audio/mpeg">
    </audio>
</body>
</html>""",
            keyBoardTips = listOf(
                "<img> একটি এম্পটি ট্যাগ যার কোনো ক্লোজিং ট্যাগ নেই।",
                "alt অ্যাট্রিবিউট দৃষ্টিহীনদের স্ক্রিন রিডারে এবং নেটওয়ার্ক ধীর থাকলে সহায়ক।",
                "<audio> ও <video> ট্যাগে controls অ্যাট্রিবিউট দিলে প্লেয়ার ইন্টারফেস আসে।"
            )
        ),

        LessonTopic(
            id = "ch4_10",
            chapter = "অধ্যায় ৪.১০",
            title = "এইচটিএমএল টেবিল ও সেল মার্জিং (বোর্ড স্পেশাল 🔥)",
            subtitle = "table, tr, th, td, border, colspan এবং rowspan এর জটিল হিসাব",
            badge = "বোর্ড স্পেশাল 🔥",
            summary = "এইচএসসি পরীক্ষায় টেবিল থেকে সর্বাধিক সৃজনশীল প্রশ্ন আসে। সারি, কলাম, বর্ডার, কলস্প্যান ও রোস্প্যান মার্জিং এর পরিপূর্ণ নিয়মাবলী।",
            sections = listOf(
                SectionContent(
                    heading = "টেবিলের মৌলিক ট্যাগসমূহ",
                    text = "• <table> : টেবিল শুরু ও শেষ করে। border অ্যাট্রিবিউট (যেমন: border=\"1\") না দিলে বর্ডার দৃশ্যমান হয় না।\n• <tr> (Table Row) : অনুভূমিক সারি তৈরি করে।\n• <th> (Table Header) : টেবিলের হেডার সেল (স্বয়ংক্রিয়ভাবে বোল্ড ও সেন্টারে থাকে)।\n• <td> (Table Data) : টেবিলের সাধারণ ডেটা সেল।"
                ),
                SectionContent(
                    heading = "Colspan ও Rowspan (সৃজনশীল প্রশ্নের মূল চাবিকাঠি)",
                    text = "• colspan: পাশাপাশি দুই বা ততোধিক কলামকে একত্রিত (Merge) করতে ব্যবহৃত হয়। যেমন: colspan=\"2\" দিলে দুটি কলাম মিলে একটি হবে।\n• rowspan: উপর-নিচ দুই বা ততোধিক সারিকে একত্রিত (Merge) করতে ব্যবহৃত হয়। যেমন: rowspan=\"2\" দিলে দুটি রো মিলে একটি হবে।\n\n📌 সোনালী নিয়ম: যে সেলে মার্জ শুরু হয় সেখানে colspan/rowspan লিখতে হবে এবং পরবর্তী সারি বা কলাম থেকে অতিরিক্ত <td> বাদ দিতে হবে।"
                ),
                SectionContent(
                    heading = "অন্যান্য গুরুত্বপূর্ণ অ্যাট্রিবিউট",
                    text = "• cellpadding: সেলের ভেতর টেক্সট ও বর্ডারের মধ্যবর্তী দূরত্ব।\n• cellspacing: একটি সেলের সাথে পাশের সেলের মধ্যবর্তী দূরত্ব।\n• align: সেলের লেখা বিন্যাস (left, center, right)।\n• bgcolor: টেবিল বা সেলের ব্যাকগ্রাউন্ড কালার নির্ধারণ।"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>এইচএসসি বোর্ড টেবিল</title>
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #f1f5f9;">
    <h2 style="color: #0f172a; text-align: center;">শিক্ষার্থীর নম্বরপত্র (বোর্ড স্টাইল টেবিল)</h2>
    
    <table border="1" cellpadding="8" style="width: 100%; border-collapse: collapse; text-align: center; background: white;">
        <tr bgcolor="#0284c7" style="color: white;">
            <th rowspan="2">রোল</th>
            <th rowspan="2">নাম</th>
            <th colspan="2">আইসিটি নম্বর</th>
        </tr>
        <tr bgcolor="#38bdf8" style="color: white;">
            <th>তত্ত্বীয়</th>
            <th>ব্যবহারিক</th>
        </tr>
        <tr>
            <td>১০১</td>
            <td>আবির</td>
            <td>৪৭</td>
            <td>২৫</td>
        </tr>
        <tr bgcolor="#f8fafc">
            <td>১০২</td>
            <td>সাদিয়া</td>
            <td>৫০</td>
            <td>২৫</td>
        </tr>
    </table>
    
    <p style="margin-top: 12px; font-size: 13px; color: #475569;">
        💡 লক্ষ্য করুন: 'রোল' ও 'নাম' সেলে <b>rowspan="2"</b> এবং 'আইসিটি নম্বর' সেলে <b>colspan="2"</b> ব্যবহার করা হয়েছে।
    </p>
</body>
</html>""",
            keyBoardTips = listOf(
                "border=\"1\" না দিলে ব্রাউজারে টেবিলের দাগ দেখা যায় না।",
                "<th> এর টেক্সট স্বভাবতই বোল্ড এবং সেন্টারে বিন্যস্ত থাকে।",
                "কলাম একত্রিত করতে colspan আর সারি একত্রিত করতে rowspan ব্যবহার হয়।"
            )
        ),

        LessonTopic(
            id = "ch4_11",
            chapter = "অধ্যায় ৪.১১",
            title = "এইচটিএমএল ফর্ম ও ইউজার ইনপুট",
            subtitle = "form, input types, radio, checkbox, submit, select ও GET বনাম POST",
            badge = "ফর্ম ডিজাইন",
            summary = "ওয়েবসাইটে ব্যবহারকারীর কাছ থেকে তথ্য সংগ্রহ করার ফর্ম তৈরি, ইনপুট টাইপ, ড্রপডাউন এবং মেথডের ভূমিকা।",
            sections = listOf(
                SectionContent(
                    heading = "ফর্মের গঠন ও অ্যাট্রিবিউট (<form>)",
                    text = "ব্যবহারকারীর ইনপুট ডাটা সংগ্রহ করে সার্ভারে পাঠাতে <form> ট্যাগ ব্যবহার করা হয়।\n• action: যে সার্ভার ফাইলে তথ্য প্রক্রিয়া হবে (যেমন: action=\"save.php\")।\n• method=\"get\": তথ্য URL এর সাথে দৃশ্যমানভাবে যায় (সংবেদনশীল তথ্যের জন্য অনুপযুক্ত)।\n• method=\"post\": তথ্য গোপনীয়ভাবে যায়, নিরাপদ ও বড় ডেটার জন্য প্রযোজ্য।"
                ),
                SectionContent(
                    heading = "ইনপুট উপাদানের প্রকারভেদ (Input Types)",
                    text = "• type=\"text\": সাধারণ এক লাইনের লেখা।\n• type=\"password\": পাসওয়ার্ড লেখার জন্য (অক্ষরগুলো ডট হয়ে যায়)।\n• type=\"radio\": একাধিক বিকল্প থেকে মাত্র একটি বেছে নিতে (সবগুলোর name এক হতে হবে)।\n• type=\"checkbox\": একাধিক বিকল্প টিক চিহ্ন দিতে।\n• type=\"submit\": ফর্ম জমা দেওয়ার বাটন।\n• type=\"reset\": ফর্ম ক্লিয়ার বা রিসেট করার বাটন।\n• <select> ও <option>: ড্রপডাউন মেনু তৈরির জন্য।\n• <textarea>: একাধিক লাইনের মন্তব্যের জন্য।"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>ছাত্রভর্তি ফর্ম</title>
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #fff1f2;">
    <h2 style="color: #be123c;">এইচএসসি পরীক্ষার রেজিস্ট্রেশন ফর্ম</h2>
    <form style="background: white; padding: 16px; border-radius: 8px; border: 1px solid #fecdd3;">
        <label>শিক্ষার্থীর নাম:</label><br>
        <input type="text" placeholder="পূর্ণ নাম লিখুন" style="width: 90%; padding: 6px; margin: 4px 0;"><br><br>
        
        <label>পাসওয়ার্ড:</label><br>
        <input type="password" placeholder="পাসওয়ার্ড দিন" style="width: 90%; padding: 6px; margin: 4px 0;"><br><br>
        
        <label>লিঙ্গ:</label><br>
        <input type="radio" name="gender" value="male" checked> পুরুষ
        <input type="radio" name="gender" value="female"> মহিলা<br><br>
        
        <label>বিভাগ:</label><br>
        <select style="padding: 6px; margin: 4px 0;">
            <option>বিজ্ঞান (Science)</option>
            <option>ব্যবসায় শিক্ষা (Business Studies)</option>
            <option>মানবিক (Humanities)</option>
        </select><br><br>
        
        <input type="submit" value="জমা দিন" style="background: #e11d48; color: white; padding: 8px 16px; border: none; border-radius: 4px; cursor: pointer;">
    </form>
</body>
</html>""",
            keyBoardTips = listOf(
                "রেডিও বাটনে কেবল একটি নির্বাচন নিশ্চিত করতে সবগুলোর name একই হতে হবে।",
                "গোপনীয় তথ্যের জন্য method=\"post\" ব্যবহার করা আদর্শ।",
                "ড্রপডাউন লিস্ট তৈরির জন্য <select> ও <option> ট্যাগ ব্যবহৃত হয়।"
            )
        ),

        LessonTopic(
            id = "ch4_12",
            chapter = "অধ্যায় ৪.১২",
            title = "ওয়েবসাইট ডিজাইনের ধাপসমূহ",
            subtitle = "পরিকল্পনা, সাইটম্যাপ, ওয়্যারফ্রেম, ইউআই ডিজাইন এবং কোডিং",
            badge = "ওয়েব ডেভেলপমেন্ট",
            summary = "একটি সফল ও পেশাদার ওয়েবসাইট তৈরির ৫টি মূল পর্যায়: পরিকল্পনা থেকে শুরু করে টেস্টিং ও ভ্যালিডেশন।",
            sections = listOf(
                SectionContent(
                    heading = "ওয়েবসাইট ডিজাইনের ৫টি মূল পর্যায়",
                    text = "একটি পূর্ণাঙ্গ ওয়েবসাইট তৈরি করতে ৫টি নির্দিষ্ট ধাপ অনুসরণ করতে হয়:\n\n১. প্রয়োজনীয়তা বিশ্লেষণ ও পরিকল্পনা (Planning & Requirement Analysis):\nওয়েবসাইটের লক্ষ্য, উদ্দিষ্ট দর্শক (Target Audience), বিষয়বস্তু এবং বাজেটের রূপরেখা তৈরি।\n\n২. সাইটম্যাপ ও স্ট্রাকচার ডিজাইন (Sitemap & Wireframe):\nওয়েবসাইটের পেজগুলো কীভাবে একে অপরের সাথে যুক্ত থাকবে তার নকশা তৈরি করা (লিনিয়ার, ট্রি ইত্যাদি কাঠামো নির্বাচন)।\n\n৩. ইউআই ও গ্রাফিক্স ডিজাইন (UI/UX Mockup):\nকালার প্যালেট, টাইপোগ্রাফি, ব্যানার এবং পেজ লেআউট তৈরি (যেমন Figma বা Photoshop এ)।\n\n৪. কোডিং ও ডেভেলপমেন্ট (Coding):\nHTML দিয়ে গঠন, CSS দিয়ে স্টাইলিং এবং JavaScript দিয়ে ইন্টারেক্টিভিটি যোগ করা।\n\n৫. টেস্টিং ও ভ্যালিডেশন (Testing & Validation):\nবিভিন্ন ডিভাইসে (মোবাইল, ট্যাবলেট, পিসি) এবং বিভিন্ন ব্রাউজারে (Chrome, Firefox, Safari) পরীক্ষা করা।"
                ),
                SectionContent(
                    heading = "রেসপনসিভ ওয়েব ডিজাইন (Responsive Design)",
                    text = "যে ওয়েবসাইট মোবাইল, ট্যাবলেট কিংবা কম্পিউটার—যেকোনো স্ক্রিন সাইজে সুন্দরভাবে মানিয়ে প্রদর্শিত হয়, তাকে রেসপনসিভ ওয়েবসাইট বলে। এর জন্য CSS3 Media Query ও Viewport মেটা ট্যাগ ব্যবহৃত হয়।"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>রেসপনসিভ লেআউট ডেমো</title>
</head>
<body style="font-family: Arial, sans-serif; margin: 0; padding: 16px; background-color: #f8fafc;">
    <!-- হেডার অংশ -->
    <div style="background: #0284c7; color: white; padding: 16px; text-align: center; border-radius: 8px;">
        <h2>ঢাকা মডেল কলেজ</h2>
        <p style="margin: 0;">এইচএসসি আইসিটি ওয়েব ডিজাইন প্রজেক্ট</p>
    </div>
    
    <!-- নেভিগেশন বার -->
    <div style="background: #0369a1; padding: 8px; margin-top: 8px; border-radius: 6px; text-align: center;">
        <a href="#" style="color: white; margin: 0 12px; text-decoration: none;">হোম</a> |
        <a href="#" style="color: white; margin: 0 12px; text-decoration: none;">ভর্তি</a> |
        <a href="#" style="color: white; margin: 0 12px; text-decoration: none;">ফলাফল</a> |
        <a href="#" style="color: white; margin: 0 12px; text-decoration: none;">যোগাযোগ</a>
    </div>

    <!-- মূল কন্টেন্ট -->
    <div style="background: white; padding: 16px; margin-top: 10px; border-radius: 8px; border: 1px solid #e2e8f0;">
        <h3>ওয়েবসাইট ডিজাইনের ধাপ:</h3>
        <p>১. পরিকল্পনা → ২. সাইটম্যাপ → ৩. ইউআই ডিজাইন → ৪. এইচটিএমএল কোডিং → ৫. টেস্টিং</p>
    </div>
</body>
</html>""",
            keyBoardTips = listOf(
                "সাইটম্যাপ হলো ওয়েবসাইটের সমস্ত পেজের কাঠামোগত ব্লুপ্রিন্ট।",
                "রেসপনসিভ ডিজাইন ওয়েব পেজকে যেকোনো ডিভাইসে মানানসই করে।",
                "কোডিং শুরুর আগে পরিকল্পনা ও ওয়্যারফ্রেম তৈরি করা অত্যন্ত আবশ্যক।"
            )
        ),

        LessonTopic(
            id = "ch4_13",
            chapter = "অধ্যায় ৪.১৩",
            title = "ওয়েবসাইট পাবলিশিং-এর ধাপসমূহ",
            subtitle = "ডোমেন নেম রেজিস্ট্রেশন, ওয়েব হোস্টিং, FTP আপলোড ও সাইট প্রকাশ",
            badge = "পাবলিশিং গাইড",
            summary = "একটি লোকাল কম্পিউটারে তৈরি ওয়েবসাইটকে বিশ্বব্যাপী ইন্টারনেটে উন্মুক্ত করার ৪টি অপরিহার্য ধাপের বিশদ বিবরণ।",
            sections = listOf(
                SectionContent(
                    heading = "ওয়েবসাইট পাবলিশিং কী?",
                    text = "নিজের কম্পিউটারে তৈরি করা ওয়েব পেজগুলোকে ইন্টারনেটের একটি ওয়েব সার্ভারে স্থানান্তর করে সর্বসাধারণের জন্য উন্মুক্ত করার প্রক্রিয়াকে ওয়েবসাইট পাবলিশিং (Website Publishing) বলে।"
                ),
                SectionContent(
                    heading = "পাবলিশিং-এর ৪টি ধারাবাহিক ধাপ (বোর্ড পরীক্ষায় অত্যন্ত গুরুত্বপূর্ণ)",
                    text = "ধাপ ১: ডোমেন নেম নির্বাচন ও রেজিস্ট্রেশন (Domain Registration):\n• ওয়েবসাইটের জন্য একটি সহজ ও অর্থপূর্ণ নাম পছন্দ করা।\n• ICANN অনুমোদিত যেকোনো ডোমেন রেজিস্ট্রার (যেমন: Namecheap, GoDaddy বা বিটিসিএল এর .bd) থেকে ফি দিয়ে ডোমেন নিবন্ধন করা।\n\nধাপ ২: ওয়েব হোস্টিং ক্রয় (Web Hosting):\n• ওয়েবসাইটের সমস্ত ফাইল ও ডেটা ইন্টারনেটে সংরক্ষিত রাখতে একটি ওয়েব সার্ভারে স্টোরেজ ও ব্যান্ডউইথ ভাড়া নেওয়াকে হোস্টিং বলে।\n• হোস্টিংয়ের ধরন:\n  - শেয়ার্ড হোস্টিং (Shared): কম খরচে সাধারণ সাইটের জন্য।\n  - ভিপিএস হোস্টিং (VPS): মাঝারি সাইটের জন্য নিয়ন্ত্রিত ভার্চুয়াল সার্ভার।\n  - ডেডিকেটেড সার্ভার (Dedicated): অত্যন্ত বড় সাইটের জন্য সম্পূর্ণ আলাদা কম্পিউটার।\n  - ক্লাউড হোস্টিং (Cloud): আধুনিক ও উচ্চ গতির হোস্টিং।\n\nধাপ ৩: ফাইল আপলোড (Uploading via FTP):\n• এফটিপি (FTP - File Transfer Protocol) ক্লায়েন্ট সফটওয়্যারের (যেমন: FileZilla, cPanel File Manager) মাধ্যমে লোকাল পিসির HTML ফাইলগুলো সার্ভারে আপলোড করা হয়।\n\nধাপ ৪: সার্চ ইঞ্জিনে সাইট সাবমিশন (Search Engine Submission):\n• Google, Bing ইত্যাদি সার্চ ইঞ্জিনে সাইটের লিংক ও সাইটম্যাপ যুক্ত করা।"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>পাবলিশিং চেকলিস্ট</title>
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #f0fdf4;">
    <h2 style="color: #15803d;">ওয়েবসাইট পাবলিশিং চেকলিস্ট</h2>
    
    <div style="background: white; padding: 14px; border-radius: 8px; border: 1px solid #bbf7d0;">
        <p>✅ <b>ধাপ ১:</b> ডোমেন নাম কেনা হলো (যেমন: <code>www.mycollege.edu.bd</code>)</p>
        <p>✅ <b>ধাপ ২:</b> হোস্টিং স্পেস নির্ধারণ করা হলো (১০ GB SSD)</p>
        <p>✅ <b>ধাপ ৩:</b> FTP (FileZilla) দিয়ে <code>index.html</code> আপলোড হলো</p>
        <p>✅ <b>ধাপ ৪:</b> গুগল সার্চ কনসোলে সাইটম্যাপ জমা দেওয়া হলো</p>
    </div>
    
    <p style="margin-top: 12px; color: #166534; font-size: 13px;">
        🎉 <b>অভিনন্দন!</b> এই ৪টি ধাপ সম্পন্ন হলেই আপনার ওয়েবসাইটটি ইন্টারনেটে লাইভ হবে।
    </p>
</body>
</html>""",
            keyBoardTips = listOf(
                "FTP এর পূর্ণরূপ File Transfer Protocol যা দিয়ে ফাইল আপলোড করা হয়।",
                "হোস্টিং হলো ওয়েব সার্ভারে ফাইল জমা রাখার ভাড়া করা ডিস্ক স্পেস।",
                "ওয়েবসাইট পাবলিশিং এর প্রথম ধাপ হলো ডোমেন নেম রেজিস্ট্রেশন।"
            )
        ),

        LessonTopic(
            id = "ch4_14",
            chapter = "অধ্যায় ৪.১৪",
            title = "সার্চ ইঞ্জিন অপ্টিমাইজেশন (SEO) ও সার্চ ইঞ্জিন",
            subtitle = "সার্চ ইঞ্জিন বনাম ব্রাউজার, এসইও কী, অন-পেজ ও অফ-পেজ এসইও",
            badge = "এসইও গাইড",
            summary = "ওয়েব ব্রাউজার ও সার্চ ইঞ্জিনের পার্থক্য, গুগল বা বিং-এ প্রথম পেজে সাইট প্রদর্শনের কৌশল এবং মেটা ট্যাগের ব্যবহার।",
            sections = listOf(
                SectionContent(
                    heading = "সার্চ ইঞ্জিন বনাম ওয়েব ব্রাউজার (এইচএসসি গুরুত্বপূর্ণ পার্থক্য)",
                    text = "• ওয়েব ব্রাউজার (Web Browser): একটি অ্যাপ বা সফটওয়্যার যা ব্যবহারকারীর ডিভাইসে ইন্সটল থাকে এবং HTML পেজ পড়ে তা স্ক্রিনে প্রদর্শন করে। উদাহরণ: Google Chrome, Mozilla Firefox, Apple Safari, Microsoft Edge।\n• সার্চ ইঞ্জিন (Search Engine): ইন্টারনেটের একটি ওয়েবসাইট যা বিশাল ডেটাবেজ স্ক্যান করে ব্যবহারকারীর সার্চ করা তথ্যের তালিকা প্রদান করে। উদাহরণ: Google, Bing, Yahoo, DuckDuckGo, পিপীলিকা (বাংলাদেশের সার্চ ইঞ্জিন)।\n(বোর্ড টিপ: গুগল ক্রোম হলো ব্রাউজার কিন্তু গুগল হলো সার্চ ইঞ্জিন!)"
                ),
                SectionContent(
                    heading = "এসইও (SEO - Search Engine Optimization)",
                    text = "সার্চ ইঞ্জিন অপ্টিমাইজেশন হলো এমন কিছু কৌশল যার মাধ্যমে কোনো ওয়েবসাইটকে সার্চ ইঞ্জিনের ফলাফলের প্রথম পাতায় শীর্ষস্থানে প্রদর্শন করানো যায় (অর্গানিক ট্রাফিকের মাধ্যমে)।\n\n১. অন-পেজ এসইও (On-Page SEO):\n• অর্থপূর্ণ টাইটেল ট্যাগ (<title>) ও মেটা ডেসক্রিপশন লেখা।\n• সঠিক হেডিং (h1, h2) এবং ইমেজের জন্য alt অ্যাট্রিবিউট ব্যবহার।\n• ভালো মানের কন্টেন্ট ও কি-ওয়ার্ড (Keywords) সন্নিবেশ।\n\n২. অফ-পেজ এসইও (Off-Page SEO):\n• অন্যান্য জনপ্রিয় ওয়েবসাইট থেকে ব্যাকলিংক (Backlink) সংগ্রহ করা।\n• সামাজিক যোগাযোগ মাধ্যমে শেয়ার বৃদ্ধি।"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>SEO ফ্রেন্ডলি এইচটিএমএল পেজ</title>
    <!-- মেটা ট্যাগ যা সার্চ ইঞ্জিন পড়ে -->
    <meta name="description" content="এইচএসসি আইসিটি ৪র্থ অধ্যায় ওয়েব ডিজাইন ও এইচটিএমএল শিক্ষা">
    <meta name="keywords" content="HSC, ICT, Chapter 4, HTML, Web Design">
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #f8fafc;">
    <h1 style="color: #0284c7;">অন-পেজ এসইও এর গুরুত্ব</h1>
    <p>সার্চ ইঞ্জিন বট (যেমন Googlebot) পেজের <b>&lt;title&gt;</b>, <b>&lt;meta&gt;</b> এবং <b>&lt;h1&gt;</b> ট্যাগ পড়ে পেজের বিষয়বস্তু নির্ধারণ করে।</p>
    
    <div style="background: white; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
        <h3 style="color: #1e40af; margin-top: 0;">গুগল সার্চ ফলাফল ভিউ (SERP):</h3>
        <p style="color: #1d4ed8; font-size: 16px; margin: 0;">এইচএসসি আইসিটি ৪র্থ অধ্যায় - পূর্ণাঙ্গ সমাধান</p>
        <p style="color: #15803d; font-size: 12px; margin: 2px 0;">https://ict.edu.bd/ch4</p>
        <p style="color: #475569; font-size: 13px; margin: 0;">এইচটিএমএল ট্যাগ, টেবিল, ফর্ম ও পাবলিশিং-এর সহজ সমাধান...</p>
    </div>
</body>
</html>""",
            keyBoardTips = listOf(
                "গুগল ক্রোম ব্রাউজার কিন্তু গুগল সার্চ ইঞ্জিন।",
                "SEO এর পূর্ণরূপ Search Engine Optimization।",
                "অন-পেজ এসইও তে <title> এবং <meta> ট্যাগ সবচেয়ে গুরুত্বপূর্ণ ভূমিকা রাখে।"
            )
        ),

        LessonTopic(
            id = "ch4_15",
            chapter = "অধ্যায় ৪.১৫",
            title = "ওয়েবসাইট রক্ষণাবেক্ষণ ও ওয়েব নিরাপত্তা",
            subtitle = "নিয়মিত আপডেট, ব্রোকেন লিংক মেরামত, সাইট ব্যাকআপ এবং SSL/TLS নিরাপত্তা",
            badge = "নিরাপত্তা ও কেয়ার",
            summary = "ওয়েবসাইট প্রকাশের পর নিয়মিত রক্ষণাবেক্ষণ, সিকিউরিটি প্যাচ, সাইবার আক্রমণ প্রতিরোধ এবং HTTP বনাম HTTPS এর গুরুত্ব।",
            sections = listOf(
                SectionContent(
                    heading = "ওয়েবসাইট রক্ষণাবেক্ষণ (Website Maintenance) কেন জরুরি?",
                    text = "ওয়েবসাইট একবার পাবলিশ করলেই কাজ শেষ হয় না। সাইট সচল ও প্রাসঙ্গিক রাখতে নিয়মিত রক্ষণাবেক্ষণ করতে হয়:\n১. তথ্য ও কন্টেন্ট হালনাগাদ (Content Update): সাম্প্রতিক নোটিশ বা তথ্য নিয়মিত যোগ করা।\n২. ব্রোকেন লিংক (Broken Links) মেরামত: যেসব পেজ মুছে গেছে বা 404 Not Found ইরোর দেখাচ্ছে তা ঠিক করা।\n৩. নিয়মিত ডেটাবেজ ও সাইট ব্যাকআপ (Backup): সার্ভার ক্র্যাশ বা বিপর্যয় ঘটলে যাতে সাইট পুনরুদ্ধার করা যায়।\n৪. ব্রাউজার ও সফটওয়্যার কম্প্যাটিবিলিটি চেক: নতুন ব্রাউজারে সাইট ঠিকমতো দেখায় কি না তা তদারকি করা।"
                ),
                SectionContent(
                    heading = "ওয়েব নিরাপত্তা ও SSL/TLS সার্টিফিকেট",
                    text = "• HTTP বনাম HTTPS:\n  - HTTP (HyperText Transfer Protocol): ডেটা সাধারণ প্লেইন টেক্সট হিসেবে পাঠায়, ফলে হ্যাকাররা তথ্য চুরি করতে পারে।\n  - HTTPS (HTTP Secure): ডেটাকে SSL (Secure Sockets Layer) বা TLS দ্বারা এনক্রিপ্ট করে পাঠায়। ব্রাউজারের অ্যাড্রেসবারে তালার প্রতীক (🔒) দেখা যায়।\n• সাইবার হুমকি প্রতিরোধ:\n  - শক্তিশালী ও জটিল অ্যাডমিন পাসওয়ার্ড ব্যবহার।\n  - টু-ফ্যাক্টর অথেনটিকেশন (2FA) চালু রাখা।\n  - সার্ভার ও প্লাগইন সবসময় আপডেট রাখা।"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>নিরাপত্তা ও রক্ষণাবেক্ষণ</title>
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #f1f5f9;">
    <h2 style="color: #0f172a;">ওয়েবসাইট রক্ষণাবেক্ষণ ও নিরাপত্তা চেকলিস্ট</h2>
    
    <div style="background: white; padding: 14px; border-radius: 8px; border-left: 5px solid #10b981;">
        <h4 style="color: #047857; margin-top: 0;">🔒 নিরাপদ সংযোগ (HTTPS):</h4>
        <p>ব্রাউজারে তালার প্রতীক নিশ্চিত করে যে ব্যবহারকারীর ক্রেডিট কার্ড বা পাসওয়ার্ড এনক্রিপ্টেড।</p>
    </div>

    <div style="background: white; padding: 14px; border-radius: 8px; border-left: 5px solid #f59e0b; margin-top: 10px;">
        <h4 style="color: #b45309; margin-top: 0;">⚙️ সাপ্তাহিক রক্ষণাবেক্ষণ:</h4>
        <p>• ডাটাবেজ ব্যাকআপ সম্পন্ন করা।<br>• কোনো লিংক ভাঙা বা 404 আছে কিনা স্ক্যান করা।</p>
    </div>
</body>
</html>""",
            keyBoardTips = listOf(
                "HTTPS এ ডেটা SSL/TLS দ্বারা এনক্রিপ্ট করা থাকে।",
                "নিয়মিত ব্যাকআপ রাখা সাইটের ডেটা হারানোর ঝুঁকি শূন্যে নামায়।",
                "404 Not Found নির্দেশ করে যে কাঙ্ক্ষিত পেজটি সার্ভারে নেই।"
            )
        ),

        LessonTopic(
            id = "ch4_16",
            chapter = "অধ্যায় ৪.১৬",
            title = "এইচএসসি বোর্ড স্পেশাল চূড়ান্ত গাইড ও সৃজনশীল টিপস",
            subtitle = "বিগত বছরের প্রশ্ন পর্যালোচনা, সাধারণ ভুল এবং পূর্ণাঙ্গ নম্বর (১০ এ ১০) পাওয়ার কৌশল",
            badge = "বোর্ড প্রস্তুতি 🔥",
            summary = "এইচএসসি পরীক্ষার বিগত বছরের বোর্ড প্রশ্ন বিশ্লেষণ, সৃজনশীল 'গ' ও 'ঘ' নম্বর উত্তরের সঠিক কৌশল এবং ট্যাগ চিট-শিট।",
            sections = listOf(
                SectionContent(
                    heading = "পরীক্ষায় যেসব ভুলে নম্বর কাটা যায়",
                    text = "১. টেবিলের border=\"1\" না লেখা (কোড নিখুঁত হলেও বর্ডার না দিলে টেবিল দাগহীন দেখায়)।\n২. <th> এর ভেতর লেখা বোল্ড করার জন্য অতিরিক্ত <b> ট্যাগ লেখা (প্রয়োজন নেই, <th> নিজেই বোল্ড)।\n৩. <ol> বা <ul> না লিখে সরাসরি <li> লিখে ফেলা।\n৪. <img> এবং <br> এর শেষ ট্যাগ খোঁজা (এগুলো Empty ট্যাগ, ক্লোজিং নেই)।\n৫. রোস্প্যান (rowspan) ব্যবহারের পর নিচের সারির কলাম সংখ্যা হিসাব না রেখে অতিরিক্ত <td> লেখা।"
                ),
                SectionContent(
                    heading = "জরুরি ট্যাগ ও সিনট্যাক্স চিট-শিট",
                    text = "• টেবিল: <table border=\"1\" cellpadding=\"5\" cellspacing=\"0\">\n• মার্জিং: <td colspan=\"2\" rowspan=\"2\" align=\"center\" bgcolor=\"yellow\">\n• লিংক: <a href=\"URL\" target=\"_blank\">লেখা</a>\n• ছবি: <img src=\"pic.jpg\" alt=\"বিকল্প টেক্সট\" width=\"200\" height=\"150\">\n• ক্রমিক লিস্ট: <ol type=\"A\" start=\"3\">\n• বুলেট লিস্ট: <ul type=\"square\">"
                )
            ),
            practiceCode = """<!DOCTYPE html>
<html>
<head>
    <title>HSC Model Test Final</title>
</head>
<body style="font-family: Arial, sans-serif; padding: 16px; background-color: #f8fafc;">
    <h2 style="color: #1e3a8a; text-align: center;">এইচএসসি আইসিটি সৃজনশীল চূড়ান্ত মডেল</h2>
    <hr>
    
    <table border="1" cellpadding="8" style="width: 100%; border-collapse: collapse; text-align: center;">
        <tr bgcolor="#dbeafe">
            <th colspan="3">চতুর্থ অধ্যায়ের পূর্ণাঙ্গ সিলেবাস</th>
        </tr>
        <tr>
            <td rowspan="2" bgcolor="#eff6ff"><b>বিষয়</b></td>
            <td>ওয়েব ডিজাইন, আইপি, ডোমেন ও কাঠামো</td>
            <td>১ম অংশ</td>
        </tr>
        <tr>
            <td>এইচটিএমএল কোডিং, পাবলিশিং ও রক্ষণাবেক্ষণ</td>
            <td>২য় অংশ</td>
        </tr>
    </table>
    
    <p style="text-align: center; margin-top: 14px; color: #15803d; font-weight: bold;">
        🏆 এই ১৬টি পাঠ নিয়মিত অনুশীলনে এইচএসসি পরীক্ষায় পুরো ১০ এ ১০ নিশ্চিত!
    </p>
</body>
</html>""",
            keyBoardTips = listOf(
                "সৃজনশীল 'গ' এবং 'ঘ' নম্বরের জন্য টেবিল এবং ফর্ম কোড সবচেয়ে বেশি গুরুত্বপূর্ণ।",
                "অ্যাট্রিবিউটের মান সবসময় ডাবল কোটেশন (যেমন border=\"1\") এর ভেতর লেখা নিরাপদ।",
                "এইচটিএমএল কেস-ইনসেনসিটিভ হলেও ছোট হাতের অক্ষরে লেখা স্ট্যান্ডার্ড।"
            )
        )
    )

    val quizList: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = 1,
            question = "এইচটিএমএল-এ সবচেয়ে ছোট আকারের হেডিং ট্যাগ কোনটি?",
            options = listOf("<h1>", "<h3>", "<h6>", "<small>"),
            correctIndex = 2,
            explanation = "এইচটিএমএল-এ ৬টি হেডিং ট্যাগ রয়েছে (h1 থেকে h6)। h1 সবচেয়ে বড় এবং h6 সবচেয়ে ছোট।"
        ),
        QuizQuestion(
            id = 2,
            question = "নিচের কোনটি এম্পটি (Empty) ট্যাগ?",
            options = listOf("<p>", "<b>", "<tr>", "<br>"),
            correctIndex = 3,
            explanation = "<br> ট্যাগের কোনো ক্লোজিং ট্যাগ বা মধ্যবর্তী কন্টেন্ট নেই, তাই এটি একটি এম্পটি ট্যাগ।"
        ),
        QuizQuestion(
            id = 3,
            question = "একটি টেবিলের পাশাপাশি দুটি কলামকে একত্রিত (Merge) করতে কোন অ্যাট্রিবিউট ব্যবহৃত হয়?",
            options = listOf("rowspan=\"2\"", "colspan=\"2\"", "colmerge=\"2\"", "span=\"2\""),
            correctIndex = 1,
            explanation = "পাশাপাশি কলাম মার্জ করার জন্য colspan এবং ওপর-নিচ সারি মার্জ করার জন্য rowspan ব্যবহৃত হয়।"
        ),
        QuizQuestion(
            id = 4,
            question = "IPv4 অ্যাড্রেস কত বিটের হয়ে থাকে?",
            options = listOf("১৬ বিট", "৩২ বিট", "৬৪ বিট", "১২৮ বিট"),
            correctIndex = 1,
            explanation = "IPv4 অ্যাড্রেস ৩২ বিট (৪টি ৮-বিট অক্টেট) এবং IPv6 অ্যাড্রেস ১২৮ বিটের হয়।"
        ),
        QuizQuestion(
            id = 5,
            question = "পানির সংকেত H₂O লেখার সঠিক HTML কোড কোনটি?",
            options = listOf("H<sup>2</sup>O", "H<sub>2</sub>O", "H<del>2</del>O", "H<small>2</small>O"),
            correctIndex = 1,
            explanation = "সাবস্ক্রিপ্ট লেখার জন্য <sub> ট্যাগ ব্যবহৃত হয়। যেমন: H<sub>2</sub>O।"
        ),
        QuizQuestion(
            id = 6,
            question = "হাইপারলিংক নতুন উইন্ডো বা ট্যাবে খোলার জন্য কোন অ্যাট্রিবিউট মান ব্যবহৃত হয়?",
            options = listOf("target=\"_self\"", "target=\"_newtab\"", "target=\"_blank\"", "href=\"_blank\""),
            correctIndex = 2,
            explanation = "target=\"_blank\" নির্ধারণ করলে ক্লিক করা লিঙ্ক নতুন উইন্ডো বা ট্যাবে ওপেন হয়।"
        ),
        QuizQuestion(
            id = 7,
            question = "স্কুল বা কলেজের ওয়েবসাইটের জন্য সবচেয়ে উপযোগী কাঠামো কোনটি?",
            options = listOf("লিনিয়ার কাঠামো", "ট্রি বা হায়ারার্কিক্যাল কাঠামো", "নেটওয়ার্ক কাঠামো", "বৃত্তাকার কাঠামো"),
            correctIndex = 1,
            explanation = "ট্রি বা হায়ারার্কিক্যাল কাঠামোতে হোম পেজের অধীনে বিভিন্ন শাখা-প্রশাখা থাকে যা শিক্ষা প্রতিষ্ঠানের জন্য সবচেয়ে উপযুক্ত।"
        ),
        QuizQuestion(
            id = 8,
            question = "আন-অর্ডার্ড লিস্ট (<ul>)-এর ডিফল্ট বুলেট টাইপ কোনটি?",
            options = listOf("circle", "square", "disc", "none"),
            correctIndex = 2,
            explanation = "<ul> ট্যাগের ডিফল্ট বুলেট টাইপ হলো disc (ভরাট বৃত্তাকার বিন্দু)।"
        ),
        QuizQuestion(
            id = 9,
            question = "রেডিও বাটনে যেকোনো একটি নির্বাচন বাধ্যতামূলক করার শর্ত কী?",
            options = listOf(
                "সবগুলোর id এক হতে হবে",
                "সবগুলোর name অ্যাট্রিবিউট এক হতে হবে",
                "সবগুলোর value এক হতে হবে",
                "type=\"single\" লিখতে হবে"
            ),
            correctIndex = 1,
            explanation = "একই গ্রুপের রেডিও বাটনগুলোর name অ্যাট্রিবিউটের মান একই হলে ব্রাউজার কেবল একটি নির্বাচন করতে দেয়।"
        ),
        QuizQuestion(
            id = 10,
            question = "ওয়েবসাইট পাবলিশিং এর প্রথম ধাপ কোনটি?",
            options = listOf(
                "সার্চ ইঞ্জিনে জমা দেওয়া",
                "এফটিপি দিয়ে ফাইল আপলোড",
                "ডোমেন নেম রেজিস্ট্রেশন",
                "ওয়েব হোস্টিং স্পেস কেনা"
            ),
            correctIndex = 2,
            explanation = "ওয়েবসাইট পাবলিশিং-এর সর্বপ্রথম ধাপ হলো একটি উপযুক্ত ডোমেন নেম নির্বাচন ও রেজিস্ট্রেশন করা।"
        ),
        QuizQuestion(
            id = 11,
            question = "ওয়েব সার্ভারে ফাইল আপলোড করার জন্য কোন প্রটোকল সবচেয়ে বেশি ব্যবহৃত হয়?",
            options = listOf("HTTP", "FTP", "SMTP", "DNS"),
            correctIndex = 1,
            explanation = "FTP (File Transfer Protocol) ব্যবহার করে কম্পিউটার থেকে সার্ভারে ওয়েব পেজ আপলোড করা হয়।"
        ),
        QuizQuestion(
            id = 12,
            question = "নিচের কোনটি সার্চ ইঞ্জিন?",
            options = listOf("Google Chrome", "Mozilla Firefox", "Safari", "Google"),
            correctIndex = 3,
            explanation = "Google হলো একটি সার্চ ইঞ্জিন, আর Google Chrome হলো একটি ওয়েব ব্রাউজার।"
        ),
        QuizQuestion(
            id = 13,
            question = "নিচের কোন ওয়েবসাইটে তথ্য ব্যবহারকারীর ক্রিয়া অনুযায়ী রিয়েল-টাইমে পরিবর্তিত হয়?",
            options = listOf("স্ট্যাটিক ওয়েবসাইট", "ডায়নামিক ওয়েবসাইট", "লিনিয়ার পেজ", "টেক্সট পেজ"),
            correctIndex = 1,
            explanation = "ডায়নামিক ওয়েবসাইটে ডেটাবেজ থাকে এবং ব্যবহারকারীর রিকোয়েস্ট অনুযায়ী কন্টেন্ট পরিবর্তিত হয়।"
        ),
        QuizQuestion(
            id = 14,
            question = "নিরাপদ ওয়েব ব্রাউজিংয়ে ডেটা এনক্রিপশনের জন্য কোন প্রটোকল ব্যবহৃত হয়?",
            options = listOf("HTTP", "FTP", "HTTPS", "IP"),
            correctIndex = 2,
            explanation = "HTTPS প্রটোকলে SSL/TLS সার্টিফিকেট ব্যবহারের মাধ্যমে সমস্ত তথ্য এনক্রিপ্ট করা থাকে।"
        ),
        QuizQuestion(
            id = 15,
            question = "একটি ওয়েব পেজ মুছে গেলে বা না পাওয়া গেলে ব্রাউজারে কোন ইরোর কোড প্রদর্শিত হয়?",
            options = listOf("200 OK", "403 Forbidden", "404 Not Found", "500 Internal Error"),
            correctIndex = 2,
            explanation = "404 Not Found হলো এইচটিটিপি স্ট্যাটাস কোড যা নির্দেশ করে কাঙ্ক্ষিত পেজটি সার্ভারে খুঁজে পাওয়া যায়নি।"
        ),
        QuizQuestion(
            id = 16,
            question = "ডোমেন নেম শেষে '.edu' থাকলে তা কোন ধরনের প্রতিষ্ঠান বোঝায়?",
            options = listOf("বাণিজ্যিক", "সরকারি", "শিক্ষাপ্রতিষ্ঠান", "সামরিক"),
            correctIndex = 2,
            explanation = "'.edu' হলো education এর সংক্ষিপ্ত রূপ যা শিক্ষাপ্রতিষ্ঠানের ডোমেন নির্দেশ করে।"
        ),
        QuizQuestion(
            id = 17,
            question = "ইমেজ বা ছবি যুক্ত করতে কোন ট্যাগ ব্যবহার করা হয়?",
            options = listOf("<image>", "<img>", "<pic>", "<src>"),
            correctIndex = 1,
            explanation = "ছবি যুক্ত করার জন্য <img> ট্যাগ ব্যবহৃত হয় এবং src অ্যাট্রিবিউটে ছবির পাথ নির্দেশ করা হয়।"
        ),
        QuizQuestion(
            id = 18,
            question = "SEO এর পূর্ণরূপ কী?",
            options = listOf(
                "Search Engine Optimization",
                "System Engine Order",
                "Search Electronic Online",
                "Site Element Optimization"
            ),
            correctIndex = 0,
            explanation = "SEO এর পূর্ণরূপ Search Engine Optimization যা সার্চ ইঞ্জিনে সাইটের র্যাংক উন্নত করে।"
        ),
        QuizQuestion(
            id = 19,
            question = "টেলিফোন ডিরেক্টরির মতো ডোমেন নেমকে আইপিতে রূপান্তর করে কোনটি?",
            options = listOf("URL", "DNS", "FTP", "HTML"),
            correctIndex = 1,
            explanation = "DNS (Domain Name System) ডোমেন নেমকে আইপি অ্যাড্রেসে রূপান্তর করে।"
        ),
        QuizQuestion(
            id = 20,
            question = "এইচটিএমএল-এ ড্রপডাউন মেনু তৈরি করতে কোন ট্যাগ ব্যবহৃত হয়?",
            options = listOf("<dropdown>", "<list>", "<select>", "<input>"),
            correctIndex = 2,
            explanation = "<select> এবং <option> ট্যাগ ড্রপডাউন মেনু তৈরিতে ব্যবহৃত হয়।"
        )
    )

    val starterTemplates = listOf(
        "বেসিক এইচটিএমএল গঠন" to """<!DOCTYPE html>
<html>
<head>
    <title>আমার প্রথম পেজ</title>
</head>
<body style="font-family: Arial; padding: 16px;">
    <h1>স্বাগতম!</h1>
    <p>এটি একটি বেসিক এইচটিএমএল পেজ।</p>
    <hr>
    <p><b>এইচএসসি আইসিটি</b> চতুর্থ অধ্যায় অনুশীলন।</p>
</body>
</html>""",

        "এইচএসসি টেবিল (Rowspan/Colspan)" to """<!DOCTYPE html>
<html>
<head>
    <title>বোর্ড টেবিল</title>
</head>
<body style="font-family: Arial; padding: 14px;">
    <h3>বোর্ড পরীক্ষার স্পেশাল টেবিল</h3>
    <table border="1" cellpadding="6" style="border-collapse: collapse; text-align: center; width: 100%;">
        <tr bgcolor="#e2e8f0">
            <th rowspan="2">রোল</th>
            <th rowspan="2">নাম</th>
            <th colspan="2">আইসিটি</th>
        </tr>
        <tr bgcolor="#cbd5e1">
            <th>তত্ত্বীয়</th>
            <th>ব্যবহারিক</th>
        </tr>
        <tr>
            <td>১০১</td>
            <td>আবির</td>
            <td>৪৮</td>
            <td>২৫</td>
        </tr>
    </table>
</body>
</html>""",

        "শিক্ষার্থী রেজিস্ট্রেশন ফর্ম" to """<!DOCTYPE html>
<html>
<body style="font-family: sans-serif; padding: 14px;">
    <h3>কলেজ ভর্তি ফর্ম</h3>
    <form>
        <label>পূর্ণ নাম:</label><br>
        <input type="text" placeholder="নাম লিখুন"><br><br>
        
        <label>পাসওয়ার্ড:</label><br>
        <input type="password" placeholder="পাসওয়ার্ড"><br><br>
        
        <label>বিভাগ:</label><br>
        <select>
            <option>বিজ্ঞান</option>
            <option>ব্যবসায় শিক্ষা</option>
            <option>মানবিক</option>
        </select><br><br>
        
        <input type="submit" value="আবেদন জমা দিন">
    </form>
</body>
</html>""",

        "অর্ডারড ও আন-অর্ডারড লিস্ট" to """<!DOCTYPE html>
<html>
<body style="font-family: Arial; padding: 14px;">
    <h3>পাঠ্যতালিকা</h3>
    <ol type="I">
        <li>বাংলা</li>
        <li>ইংরেজি</li>
        <li>তথ্য ও যোগাযোগ প্রযুক্তি</li>
    </ol>
    <hr>
    <ul type="square">
        <li>প্রথম পত্র</li>
        <li>দ্বিতীয় পত্র</li>
    </ul>
</body>
</html>""",

        "ওয়েবসাইট পাবলিশিং কার্ড ডেমো" to """<!DOCTYPE html>
<html>
<head>
    <title>ওয়েবসাইট পাবলিশিং</title>
</head>
<body style="font-family: Arial; padding: 16px; background: #f0fdf4;">
    <div style="background: white; padding: 16px; border-radius: 8px; border: 2px solid #22c55e;">
        <h2 style="color: #15803d; margin-top: 0;">🌐 ওয়েবসাইট পাবলিশিং লাইভ</h2>
        <p>ডোমেন: <b>www.myschool.edu.bd</b></p>
        <p>সার্ভার প্রটোকল: <b>HTTPS (নিরাপদ SSL)</b></p>
        <p>স্ট্যাটাস: <span style="background: #dcfce7; color: #166534; padding: 2px 6px; border-radius: 4px; font-weight: bold;">সক্রিয় ও সচল</span></p>
    </div>
</body>
</html>"""
    )
}
