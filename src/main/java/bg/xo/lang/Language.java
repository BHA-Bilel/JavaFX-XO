package bg.xo.lang;

import bg.xo.MainApp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Language {

    public static LANGNAME LANG_NAME;

    public static StringProperty
            COINCHE, DOMINOS, CONNECT4, XO, CHESS, CHECKERS,
            SKIP_H, SKIP_C, GO_BACK, SKIP_BT,
            LANG_H, LANG_C,
            THEME_H, THEME_C, LIGHT, DARK,
            WELCOME_H, WELCOME_C, WELCOME_BACK,
            SHORTCUTS_H, SHORTCUTS_C,
            HOLD_H, HOLD_C,
            CONNECT_H, CONNECT_C,
            MIGRATION_H1, MIGRATION_C1, MIGRATION_H2, MIGRATION_C2,
            LOCAL, ONLINE,
            CANCEL, OK, ACCEPT, PREVIOUS, NEXT, DONE,
            GS_NA_C, CR_MS_H, CR_MS_C,
            COMM_ERROR_H, COMM_ERROR_C,
            CNT_ACCESS_H, CNT_ACCESS_C,
            CNT_SG_H, CNT_SG_C1, CNT_SG_C2,
            RETRY, ENTER_IP_PORT, IP_H, PORT_H,
            JOIN_ERROR, GONE_PRIVATE, NO_ROOMS, CHK_INTERNET,

    WINDOW, SWITCH_FS, SWITCH_THEME,

    SHORTCUTS, GENERAL, MAIN, JOIN, ROOM, GAME,

    GEN_SH_T, GEN_SH_H, GEN_SH_C,
            M_SH_T, M_SH_H, M_SH_C,
            J_SH_T, J_SH_H, J_SH_C,
            R_SH_T, R_SH_H1, R_SH_H2, R_SH_C1, R_SH_C2, OPEN_CH,
            G_SH_T, G_SH_H, G_SH_C,
            VIEW_SC, GR_H, DRAWS,
            END_GAME, GE_H, GE_C,

    LANGUAGE, ENGLISH, FRENCH, ARABIC,

    DOWNLOAD_UPDATE, UPDATE, CHECK, DOWNLOAD, DOWNLOAD_NOW,
            DU_XO_H, DU_CHECKERS_H, DU_CHESS_H, DU_CONNECT4_H, DU_DOMINOS_H, DU_COINCHE_H,
            DU_XO_C, DU_CHECKERS_C, DU_CHESS_C, DU_CONNECT4_C, DU_DOMINOS_C, DU_COINCHE_C,

    ABOUT, ABOUT_ME, ABOUT_T, ABOUT_H, ABOUT_C, VISIT_GH, VISIT_LI,
            HELP, HELP_H, HELP_C, HELP_BT,
            FEEDBACK, FEEDBACK_H, FEEDBACK_C, FEEDBACK_BT,
            COPYRIGHT, COPYRIGHT_T, COPYRIGHT_H, COPYRIGHT_C, COPYRIGHT_BT,

    HOST, HOST_H, ALL_TAKEN,
            USERNAME, ENTER_UN, UN_H1, UN_C1, UN_H2, UN_C2,
            RID_H1, RID_C1, RID_H2, RID_C2,
            JOIN_PUB, JOIN_SPEC, ROOM_ID,

    PLAYERS, REFRESH, RET_HOME,

    ROOM_H, ROOM_C,
            CH_NAME, NAME_H, NAME_C,
            COPY,
            TAKE_PLACE, READY, START_GAME,
            HOST_PRIV,
            ROOM_PR, PRIVATE, PUBLIC,
            KICK_PLAYER, KICK_H1, KICK_C1, KICK_H2, KICK_C2,

    CHAT_T, CHAT_H1, CHAT_C1, CHAT_H2, CHAT_C2,

    NOTIFICATIONS, JOINED_NOTIF, LEFT_NOTIF;

    public static void init() {
        COINCHE = new SimpleStringProperty();
        DOMINOS = new SimpleStringProperty();
        CONNECT4 = new SimpleStringProperty();
        XO = new SimpleStringProperty();
        CHESS = new SimpleStringProperty();
        CHECKERS = new SimpleStringProperty();
        SKIP_H = new SimpleStringProperty();
        SKIP_C = new SimpleStringProperty();
        GO_BACK = new SimpleStringProperty();
        SKIP_BT = new SimpleStringProperty();
        LANG_H = new SimpleStringProperty();
        LANG_C = new SimpleStringProperty();
        THEME_H = new SimpleStringProperty();
        THEME_C = new SimpleStringProperty();
        LIGHT = new SimpleStringProperty();
        DARK = new SimpleStringProperty();
        WELCOME_H = new SimpleStringProperty();
        WELCOME_C = new SimpleStringProperty();
        WELCOME_BACK = new SimpleStringProperty();
        SHORTCUTS_H = new SimpleStringProperty();
        SHORTCUTS_C = new SimpleStringProperty();
        HOLD_H = new SimpleStringProperty();
        HOLD_C = new SimpleStringProperty();
        CONNECT_H = new SimpleStringProperty();
        CONNECT_C = new SimpleStringProperty();
        LOCAL = new SimpleStringProperty();
        ONLINE = new SimpleStringProperty();
        CANCEL = new SimpleStringProperty();
        OK = new SimpleStringProperty();
        ACCEPT = new SimpleStringProperty();
        GS_NA_C = new SimpleStringProperty();
        CR_MS_H = new SimpleStringProperty();
        CR_MS_C = new SimpleStringProperty();
        RETRY = new SimpleStringProperty();
        ENTER_IP_PORT = new SimpleStringProperty();
        IP_H = new SimpleStringProperty();
        PORT_H = new SimpleStringProperty();
        WINDOW = new SimpleStringProperty();
        SWITCH_FS = new SimpleStringProperty();
        LANGUAGE = new SimpleStringProperty();
        ENGLISH = new SimpleStringProperty();
        FRENCH = new SimpleStringProperty();
        ARABIC = new SimpleStringProperty();
        //        byte[] bytes = StringUtils.getBytesUtf8(germanString);
//        String utf8String = StringUtils.newStringUtf8(bytes);
        ARABIC.set("العربية");
        FRENCH.set("Francais");
        ENGLISH.set("English");
        SWITCH_THEME = new SimpleStringProperty();
        SHORTCUTS = new SimpleStringProperty();
        GENERAL = new SimpleStringProperty();
        MAIN = new SimpleStringProperty();
        JOIN = new SimpleStringProperty();
        ROOM = new SimpleStringProperty();
        GAME = new SimpleStringProperty();
        ABOUT = new SimpleStringProperty();
        HELP = new SimpleStringProperty();
        ABOUT_ME = new SimpleStringProperty();
        FEEDBACK = new SimpleStringProperty();
        COPYRIGHT = new SimpleStringProperty();
        GEN_SH_T = new SimpleStringProperty();
        GEN_SH_H = new SimpleStringProperty();
        GEN_SH_C = new SimpleStringProperty();
        M_SH_T = new SimpleStringProperty();
        M_SH_H = new SimpleStringProperty();
        M_SH_C = new SimpleStringProperty();
        HOST = new SimpleStringProperty();
        JOIN_PUB = new SimpleStringProperty();
        JOIN_SPEC = new SimpleStringProperty();
        ROOM_ID = new SimpleStringProperty();
        J_SH_T = new SimpleStringProperty();
        J_SH_H = new SimpleStringProperty();
        J_SH_C = new SimpleStringProperty();
        REFRESH = new SimpleStringProperty();
        RET_HOME = new SimpleStringProperty();
        R_SH_T = new SimpleStringProperty();
        R_SH_H1 = new SimpleStringProperty();
        R_SH_H2 = new SimpleStringProperty();
        R_SH_C1 = new SimpleStringProperty();
        R_SH_C2 = new SimpleStringProperty();
        OPEN_CH = new SimpleStringProperty();
        CH_NAME = new SimpleStringProperty();
        NAME_H = new SimpleStringProperty();
        NAME_C = new SimpleStringProperty();
        TAKE_PLACE = new SimpleStringProperty();
        NEXT = new SimpleStringProperty();
        PREVIOUS = new SimpleStringProperty();
        DONE = new SimpleStringProperty();
        KICK_PLAYER = new SimpleStringProperty();
        START_GAME = new SimpleStringProperty();
        G_SH_T = new SimpleStringProperty();
        G_SH_H = new SimpleStringProperty();
        G_SH_C = new SimpleStringProperty();
        VIEW_SC = new SimpleStringProperty();
        END_GAME = new SimpleStringProperty();
        DOWNLOAD_UPDATE = new SimpleStringProperty();
        UPDATE = new SimpleStringProperty();
        CHECK = new SimpleStringProperty();
        DOWNLOAD = new SimpleStringProperty();
        DU_XO_H = new SimpleStringProperty();
        DU_CHECKERS_H = new SimpleStringProperty();
        DU_CHESS_H = new SimpleStringProperty();
        DU_CONNECT4_H = new SimpleStringProperty();
        DU_DOMINOS_H = new SimpleStringProperty();
        DU_COINCHE_H = new SimpleStringProperty();
        DU_XO_C = new SimpleStringProperty();
        DU_CHECKERS_C = new SimpleStringProperty();
        DU_CHESS_C = new SimpleStringProperty();
        DU_CONNECT4_C = new SimpleStringProperty();
        DU_DOMINOS_C = new SimpleStringProperty();
        DU_COINCHE_C = new SimpleStringProperty();
        DOWNLOAD_NOW = new SimpleStringProperty();
        ABOUT_T = new SimpleStringProperty();
        ABOUT_H = new SimpleStringProperty();
        ABOUT_C = new SimpleStringProperty();
        VISIT_GH = new SimpleStringProperty();
        VISIT_LI = new SimpleStringProperty();
        HELP_H = new SimpleStringProperty();
        HELP_C = new SimpleStringProperty();
        HELP_BT = new SimpleStringProperty();
        FEEDBACK_H = new SimpleStringProperty();
        FEEDBACK_C = new SimpleStringProperty();
        FEEDBACK_BT = new SimpleStringProperty();
        COPYRIGHT_T = new SimpleStringProperty();
        COPYRIGHT_H = new SimpleStringProperty();
        COPYRIGHT_C = new SimpleStringProperty();
        COPYRIGHT_BT = new SimpleStringProperty();
        USERNAME = new SimpleStringProperty();
        UN_H1 = new SimpleStringProperty();
        UN_C1 = new SimpleStringProperty();
        UN_H2 = new SimpleStringProperty();
        UN_C2 = new SimpleStringProperty();
        RID_H1 = new SimpleStringProperty();
        RID_C1 = new SimpleStringProperty();
        RID_H2 = new SimpleStringProperty();
        RID_C2 = new SimpleStringProperty();
        HOST_H = new SimpleStringProperty();
        CHK_INTERNET = new SimpleStringProperty();
        ALL_TAKEN = new SimpleStringProperty();
        ROOM_H = new SimpleStringProperty();
        ROOM_C = new SimpleStringProperty();
        COMM_ERROR_H = new SimpleStringProperty();
        COMM_ERROR_C = new SimpleStringProperty();
        CHAT_T = new SimpleStringProperty();
        CHAT_H1 = new SimpleStringProperty();
        CHAT_C1 = new SimpleStringProperty();
        CHAT_H2 = new SimpleStringProperty();
        CHAT_C2 = new SimpleStringProperty();
        GR_H = new SimpleStringProperty();
        DRAWS = new SimpleStringProperty();
        JOIN_ERROR = new SimpleStringProperty();
        GONE_PRIVATE = new SimpleStringProperty();
        NO_ROOMS = new SimpleStringProperty();
        PLAYERS = new SimpleStringProperty();
        ENTER_UN = new SimpleStringProperty();
        COPY = new SimpleStringProperty();
        NOTIFICATIONS = new SimpleStringProperty();
        JOINED_NOTIF = new SimpleStringProperty();
        LEFT_NOTIF = new SimpleStringProperty();
        MIGRATION_H1 = new SimpleStringProperty();
        MIGRATION_C1 = new SimpleStringProperty();
        MIGRATION_H2 = new SimpleStringProperty();
        MIGRATION_C2 = new SimpleStringProperty();
        HOST_PRIV = new SimpleStringProperty();
        ROOM_PR = new SimpleStringProperty();
        PRIVATE = new SimpleStringProperty();
        PUBLIC = new SimpleStringProperty();
        KICK_H1 = new SimpleStringProperty();
        KICK_C1 = new SimpleStringProperty();
        KICK_H2 = new SimpleStringProperty();
        KICK_C2 = new SimpleStringProperty();
        CNT_ACCESS_H = new SimpleStringProperty();
        CNT_ACCESS_C = new SimpleStringProperty();
        GE_H = new SimpleStringProperty();
        GE_C = new SimpleStringProperty();
        CNT_SG_H = new SimpleStringProperty();
        CNT_SG_C1 = new SimpleStringProperty();
        CNT_SG_C2 = new SimpleStringProperty();
        READY = new SimpleStringProperty();
    }

    public static void load_lang(LANGNAME lang) {
        LANG_NAME = lang;
        switch (lang) {
            case ENGLISH:
                load_english_strings();
                break;
            case ARABIC:
                load_arabic_strings();
                break;
            case FRENCH:
                load_french_strings();
                break;
        }
    }

    public static String unread_msg(int no) {
        switch (LANG_NAME) {
            case ENGLISH:
                return "You have " + no + " unread messages, open chat to read them !";
            case FRENCH:
                return "Vous avez " + no + " messages non lus, ouvrez le chat pour les lire !";
            case ARABIC:
                return "لديك " + no + " رسائل غير مقروءة ، افتح الدردشة لقراءتها !"; // todo
        }
        return null;
    }

    public static String kicked_notif(String name, boolean isYou) {
        if (isYou)
            switch (LANG_NAME) {
                case ENGLISH:
                    return "You were kicked of the room";
                case FRENCH:
                    return "Vous avez été expulsé de la salle";
                case ARABIC:
                    return "تم طردك من الغرفة";
            }
        else
            switch (LANG_NAME) {
                case ENGLISH:
                    return name + " was kicked of the room";
                case FRENCH:
                    return name + " a été expulsé de la salle";
                case ARABIC:
                    return "تم طرد " + name + " من الغرفة";
            }
        return null;
    }

    public static StringProperty unav() {
        StringProperty unav = new SimpleStringProperty();
        switch (LANG_NAME) {
            case ENGLISH:
                unav.set(MainApp.GAME_NAME + " server is not available at the moment");
            case FRENCH:
                unav.set("le serveur " + MainApp.GAME_NAME + " n'est pas disponible pour le moment");
            case ARABIC:
                unav.set("خادم " + MainApp.GAME_NAME + " غير متوفر في الوقت الحالي");
        }
        return unav;
    }

    private static void load_arabic_strings() {
        COINCHE.set("بلوت الفرنسية");
        DOMINOS.set("الدومينو");
        CONNECT4.set("4 على التوالي");
        XO.set("تيك تاك تو");
        CHESS.set("شطرنج");
        CHECKERS.set("لعبة الداما");
        SKIP_H.set("هل تريد حقًا تخطي البرنامج التعليمي؟");
        SKIP_C.set("هذه تجربة فريدة ، يرجى إكمالها من أجل مصلحتك ،"
                + "\n"
                + " لن يستغرق الأمر وقتًا طويلاً!");
        GO_BACK.set("تابع البرنامج التعليمي");
        SKIP_BT.set("تخطي البرنامج التعليمي");
        LANG_H.set("مرحبا!");
        LANG_C.set("الرجاء اختيار لغتك المفضلة للمتابعة ، يمكن دائمًا تغيير هذا لاحقًا");
        THEME_H.set("اي لغة تفضل؟");
        THEME_C.set("اختر بين الوضع الفاتح والداكن لواجهة المستخدم، ويمكن تغيير ذلك لاحقًا");
        LIGHT.set("فاتح");
        DARK.set("داكن");
        WELCOME_H.set("نحن على وشك الانتهاء! اسمحوا لي أن أقدم لكم لعبتي");
        WELCOME_C.set("أهلا! أنا بن حاج عمار بلال ، مطور هذه اللعبة"
                + "\n"
                + "\n"
                + "لعبة تيك تاك تو الخاصة بي ليست لعبة عادية!" // todo change in other bg
                + "\n"
                + "يأتي في موضوعين وثلاث لغات"
                + "\n"
                + "إنه يوفر تعدد لاعبين حقيقي ، بدون ذكاء اصطناعي ، فقط أشخاص حقيقيون"
                + "\n"
                + "يمكن لعبها محليًا ، أو عبر الإنترنت"
                + "\n"
                + "يحتوي على تطبيق دردشة مدمج حتى تتمكن من تكوين صداقات جديدة ، والبقاء على اتصال أثناء اللعب"
                + "\n"
                + "\n"
                + "للحصول على أفضل تجربة ، لا تنس البحث عن التحديثات من وقت لآخر"
                + "\n");
        WELCOME_BACK.set("مرحبا بعودتك! :)");
        SHORTCUTS_H.set("أنت الآن جاهز!");
        SHORTCUTS_C.set("شكرًا لك على صبرك ، يمكنك الآن بدء اللعب ، استمتع ؛)");
        HOLD_H.set("الرجاء الانتظار...");
        HOLD_C.set("شكرا");
        CONNECT_H.set("الرجاء الانتظار");
        CONNECT_C.set("الاتصال بالخادم...");
        LOCAL.set("محلي");
        ONLINE.set("متصل");
        CANCEL.set("إلغاء");
        OK.set("حسنا");
        ACCEPT.set("قبول");
        GS_NA_C.set("يرجى العودة لاحقًا");
        CR_MS_H.set("تعذر الوصول إلى الخادم!");
        CR_MS_C.set("تحقق من اتصالك بالإنترنت ، واختر أحد الخيارات التالية");
        RETRY.set("إعادة المحاولة");
        ENTER_IP_PORT.set("أدخل عنوان IP / المنفذ للخادم");
        IP_H.set("أدخل عنوان IP الخادم");
        PORT_H.set("أدخل منفذ الخادم");
        WINDOW.set("النافذة");
        SWITCH_FS.set("تبديل وضع ملء الشاشة");
        SWITCH_THEME.set("تبديل واجهة المستخدم");
        LANGUAGE.set("اللغة");
        SHORTCUTS.set("الاختصارات");
        GENERAL.set("الاختصارات العامة");
        MAIN.set("الرئيسية");
        JOIN.set("الانضمام");
        ROOM.set("الغرفة");
        GAME.set("اللعبة");
        ABOUT.set("عن البرنامج");
        HELP.set("مساعدة");
        ABOUT_ME.set("عن المطور");
        FEEDBACK.set("إبداء رأيك حول البرنامج");
        COPYRIGHT.set("حقوق التأليف");
        DOWNLOAD_UPDATE.set("تحميل/تحديث");
        VIEW_SC.set("عرض النتيجة");
        END_GAME.set("انهاء اللعبة");
        GEN_SH_T.set("الاختصارات العامة");
        GEN_SH_H.set("فيما يلي بعض الاختصارات العامة");
        GEN_SH_C.set("F1"
                + "\n         كيف ألعب"
                + "\n"
                + "\nF2"
                + "\n         التبديل بين المظهر الفاتح / الداكن"
                + "\n"
                + "\nF11"
                + "\n         تبديل وضع ملء الشاشة");
        M_SH_T.set("اختصارات الرئيسية");
        M_SH_H.set("فيما يلي بعض الاختصارات التي يمكنك استخدامها أثناء وجودك في القائمة الرئيسية");
        M_SH_C.set("Ctrl + H"
                + "\n         استضف غرفة"
                + "\n"
                + "\nCtrl + J"
                + "\n         الانضمام إلى الغرف العامة / المحلية"
                + "\n"
                + "\nCtrl + O"
                + "\n         التبديل إلى الوضع المتصل"
                + "\n"
                + "\nCtrl + L"
                + "\n         التبديل إلى الوضع المحلي"
                + "\n"
                + "\nCtrl + R"
                + "\n         الانضمام إلى غرفة محددة (الوضع المتصل فقط)");
        J_SH_T.set("اختصارات الانضمام");
        J_SH_H.set("فيما يلي بعض الاختصارات التي يمكنك استخدامها أثناء وجودك في قائمة الانضمام");
        J_SH_C.set("Ctrl + NUMPAD (1-5)"
                + "\n         الانضمام إلى الغرفة رقم"
                + "\n"
                + "\nCtrl + R"
                + "\n         تحديث"
                + "\n"
                + "\nCtrl + N"
                + "\n         إظهار الغرف التالية (الوضع المحلي فقط)"
                + "\n"
                + "\nCtrl + H"
                + "\n         الرجوع إلى الرئيسية");
        R_SH_T.set("اختصارات الغرفة");
        R_SH_H1.set("إليك بعض الاختصارات التي يمكنك استخدامها ما دمت في غرفة"
                + "\n"
                + ", انقر التالي للمزيد");
        R_SH_H2.set("إليك بعض الاختصارات التي يمكنك استخدامها ما دمت في غرفة");
        R_SH_C1.set("Ctrl + C"
                + "\n         فتح الدردشة (متاح أيضًا أثناء اللعب)"
                + "\n"
                + "\nCtrl + N"
                + "\n         تغيير الإسم"
                + "\n"
                + "\nCtrl + Arrow keys (أسفل ، يمين ، أعلى ، يسار)"
                + "\n         إحجز المكان الفارغ"
                + "\n"
                + "\nCtrl + I"
                + "\n         نسخ معرف الغرفة (الوضع المتصل فقط)"
                + "\n"
                + "\nCtrl + R"
                + "\n         تبديل حالة الاستعداد");
        R_SH_C2.set("Ctrl + T"
                + "\n         إظهار / حجب الإشعارات"
                + "\n"
                + "\nCtrl + K"
                + "\n         طرد شخص ما (للمشرفين فقط) (متاح أيضًا أثناء اللعب)"
                + "\n"
                + "\nCtrl + P"
                + "\n         تغيير الخصوصية (للمسؤولين فقط)"
                + "\n"
                + "\nCtrl + S"
                + "\n         بدء اللعبة (للمشرفين فقط)"
                + "\n"
                + "\nCtrl + H"
                + "\n         الرجوع إلى الرئيسية");
        NEXT.set("التالي");
        PREVIOUS.set("السابق");
        DONE.set("إنتهاء");
        G_SH_T.set("اختصارات اللعبة");
        G_SH_H.set("فيما يلي بعض الاختصارات التي يمكن استخدامها أثناء اللعب");
        G_SH_C.set("Ctrl + V" +
                "\n         عرض النتيجة"
                + "\n"
                + "\nCtrl + E"
                + "\n         انهاء اللعبة (العودة للغرفة)");

        UPDATE.set("تحديث");
        DOWNLOAD.set("تحميل");
        CHECK.set("التحقق");
        DOWNLOAD_NOW.set("حمل الان");
        DU_XO_H.set("تأكد من أن لديك أحدث إصدار من تيك تاك تو"); // todo change in other bg
        DU_XO_C.set("نسختك الحالية هي: " + MainApp.CURRENT_VERSION + " ، تحقق من وجود إصدار جديد");
        DU_CHECKERS_H.set("تريد أن تلعب الداما مع أصدقائك؟");
        DU_CHECKERS_C.set("قم بتنزيل لعبة الداما الخاصة بي مجانًا");
        DU_CHESS_H.set("تريد أن تلعب الشطرنج مع أصدقائك؟");
        DU_CHESS_C.set("قم بتنزيل لعبة الشطرنج الخاصة بي مجانًا");
        DU_CONNECT4_H.set("هل تريد أن تلعب لعبة 4 على التوالي مع أصدقائك؟");
        DU_CONNECT4_C.set("قم بتنزيل لعبة 4 على التوالي الخاصة بي مجانًا");
        DU_DOMINOS_H.set("تريد أن تلعب الدومينو مع أصدقائك؟");
        DU_DOMINOS_C.set("قم بتنزيل لعبة الدومينو الخاصة بي مجانًا");
        DU_COINCHE_H.set("تريد أن تلعب بلوت الفرنسية مع أصدقائك؟");
        DU_COINCHE_C.set("قم بتنزيل لعبة بلوت الفرنسية الخاصة بي مجانًا");
        ABOUT_T.set("بلال بن. يرسل تحياته");
        ABOUT_H.set("مرحبا! أنا بن حاج أعمر بلال");
        ABOUT_C.set("شكرا لاستخدام لعبتي :)\n" +
                "معرفة المزيد عني:");
        VISIT_GH.set("زيارة حسابي في GitHub");
        VISIT_LI.set("زيارة حسابي في LinkedIn");
        HELP_H.set("لا تعرف كيفية استخدام التطبيق؟");
        HELP_C.set("تعلم كيفية اللعب");
        HELP_BT.set("انا بحاجة الى مساعدة!");
        FEEDBACK_H.set("ما رأيك في هذا المشروع؟ اترك أفكارك / توصياتك!");
        FEEDBACK_C.set("يجب أن يكون لديك حساب على GitHub");
        FEEDBACK_BT.set("إيداع رأيك!");
        COPYRIGHT_T.set("حقوق التأليف");
        COPYRIGHT_H.set("تحتوي هذه اللعبة على شفرة مصدر غير مرخصة عن عمد (وليست مفتوحة المصدر) ،\n"
                + "لا أعتبره إلا مشروعًا جانبيًا شخصيًا وطريقة لعرض مهاراتي.\n"
                + "يمكنك بالتأكيد لعب لعبتي بكل سرور ، أو مشاهدة كيف صنعتها على GitHub.");
        COPYRIGHT_C.set("لكني لا أقبل أي نوع من الاستخدام (تجاري ، براءة اختراع ، خاص) ،\n"
                + "، توزيع أو تعديل الكود المصدري لهذه اللعبة.\n"
                + "\n"
                + "للحصول على اتفاقية ترخيص خاصة ، يرجى الاتصال بي على: bilel.bha.pro@gmail.com");
        COPYRIGHT_BT.set("شاهد المشروع على GitHub");

        USERNAME.set("اسم المستخدم");
        HOST.set("استضف غرفة");
        JOIN_PUB.set("انضم إلى الغرف العامة");
        ROOM_ID.set("معرف الغرفة");
        JOIN_SPEC.set("انضم الى الغرفة");
        UN_H1.set("لا يمكن لإسم المستخدم أن يكون فارغا!");
        UN_C1.set("أسماء المستخدمين الصالحة تتكون من 1 إلى 20 حرفًا");
        UN_H2.set("اسم المستخدم طويل جدا!");
        UN_C2.set("يجب ألا يتجاوز اسم المستخدم 20 حرفًا");
        RID_H1.set("معرف الغرفة فارغ!");
        RID_C1.set("أدخل معرف غرفة للانضمام إلى غرفة");
        RID_H2.set("معرف الغرفة غير صالح");
        RID_C2.set("هذه الغرفة غير موجودة!");
        HOST_H.set("لا يمكنك استضافة غرفة");
        CHK_INTERNET.set("الرجاء التحقق من اتصال الانترنت الخاص بك");
        ALL_TAKEN.set("لسوء الحظ ، جميع الغرف مأخوذة."
                + "\n"
                + "يرجى المحاولة لاحقًا ، أو التبديل إلى الوضع المحلي");
        ROOM_H.set("لا يمكنك الانضمام إلى الغرفة");
        ROOM_C.set("إما أنها ممتلئة أو غير موجودة");
        COMM_ERROR_H.set("هناك خطأ ما!");
        COMM_ERROR_C.set("حدث خطأ أثناء الاتصال بالخادم");

        CHAT_T.set("الدردشة");
        CHAT_H1.set("رسالة فارغة!");
        CHAT_C1.set("أدخل نص لإرساله");
        CHAT_H2.set("الرسالة التي أدخلتها طويلة جدًا!");
        CHAT_C2.set("الرجاء إرسال ما لا يزيد عن 50 حرفًا في المرة الواحدة");

        GR_H.set("نتائج اللعبة");
        DRAWS.set("تعادلات : ");

        REFRESH.set("تحديث");
        RET_HOME.set("الرجوع إلى الرئيسية");
        JOIN_ERROR.set("حدث خطأ عند الانضمام إلى الغرفة");
        GONE_PRIVATE.set("أصبحت هذه الغرفة خاصة");
        NO_ROOMS.set("لا توجد غرف متاحة في الوقت الحالي ،\nيرجى العودة لاحقًا.");
        PLAYERS.set(" لاعبين");
        OPEN_CH.set("فتح الدردشة");
        CH_NAME.set("تغيير الإسم");
        NAME_H.set("لقد أدخلت نفس الاسم!");
        NAME_C.set("لديك هذا الاسم بالفعل");
        ENTER_UN.set("أدخل اسم مستخدم صالح");
        TAKE_PLACE.set("إحجز المكان");
        COPY.set("نسخ");
        NOTIFICATIONS.set("الإشعارات");

        HOST_PRIV.set("امتيازات المضيف");
        ROOM_PR.set("خصوصية الغرفة");
        PRIVATE.set("خاص");
        PUBLIC.set("عام");
        KICK_PLAYER.set("طرد لاعب");
        START_GAME.set("بدء اللعبة");

        KICK_H1.set("لا يمكنك طرد أي شخص");
        KICK_C1.set("أنت الوحيد في الغرفة!");
        KICK_H2.set("حدد اللاعب الذي تريد طرده من الغرفة");
        KICK_C2.set("لا يزال بإمكانه الانضمام إلى الغرفة");

        JOINED_NOTIF.set(" انضم الغرفة");
        LEFT_NOTIF.set(" غادر الغرفة");
        MIGRATION_H1.set("بدأ الترحيل");
        MIGRATION_C1.set("يرجى انتظار الترحيل إلى غرفة جديدة");
        MIGRATION_H2.set("انتهى الترحيل");
        MIGRATION_C2.set("أنت الآن متصل بغرفة المضيف الجديد");
        CNT_ACCESS_H.set("لا يمكنك الوصول إلى هذه الغرفة");
        CNT_ACCESS_C.set("الغرفة التي تحاول الوصول إليها مخصصة للعبة أخرى");
        GE_H.set("انتهت اللعبة!");
        GE_C.set("أدى حدث إلى إنهاء اللعبة");
        CNT_SG_H.set("لا يمكنك بدء اللعبة حاليا!");
        CNT_SG_C1.set("يجب أن يكون جميع اللاعبين جاهزين لبدء اللعبة");
        CNT_SG_C2.set("يرجى الانتظار حتى ينضم إليك خصمك");
        READY.set("حالة الاستعداد");
    }

    private static void load_french_strings() {
        COINCHE.set("Coinche");
        DOMINOS.set("Dominos");
        CONNECT4.set("Connecte 4");
        XO.set("xo");
        CHESS.set("Jeu d'échecs");
        CHECKERS.set("Dames");
        SKIP_H.set("Voulez-vous vraiment passer le tutoriel ?");
        SKIP_C.set("Il s'agit d'une expérience unique, veuillez la compléter pour votre intérêt,"
                + "\n"
                + " cela ne prendra pas longtemps!");
        GO_BACK.set("Continuer le tutoriel");
        SKIP_BT.set("Passer le tutoriel");
        LANG_H.set("Bienvenu!");
        LANG_C.set("Veuillez choisir votre langue préférée pour continuer, cela peut toujours être modifié plus tard");
        THEME_H.set("quelle langue préférez-vous?");
        THEME_C.set("Choisissez entre le mode clair et sombre, cela peut toujours être modifié plus tard");
        LIGHT.set("Clair");
        DARK.set("Sombre");
        WELCOME_H.set("Nous sommes presque terminé ! laisse moi te présenter mon jeu");
        WELCOME_C.set("Salut! Je suis BENHADJ AMAR Bilel, le développeur de ce jeu"
                + "\n"
                + "\n"
                + "Mon jeu xo n'est pas un jeu ordinaire !" // todo change in other bg
                + "\n"
                + "Il se décline en deux thèmes et trois langues"
                + "\n"
                + "Il offre un vrai multijoueur, aucune IA incluse, seulement de vraies personnes"
                + "\n"
                + "Il peut être joué localement dans un réseau local ou en ligne via Internet"
                + "\n"
                + "Il contient une application de chat intégrée pour vous permettre d'avoir de nouveaux amis,"
                + "\n"
                + " et de rester connecté tout en jouant"
                + "\n"
                + "\n"
                + "Pour la meilleure expérience, n'oubliez pas de vérifier les mises à jour de temps en temps"
                + "\n");
        WELCOME_BACK.set("Content de te revoir! :)");
        SHORTCUTS_H.set("Vous êtes prêt maintenant !");
        SHORTCUTS_C.set("Merci pour votre patience, vous pouvez maintenant commencer à jouer, Amusez-vous bien ;)");
        HOLD_H.set("Veuillez patienter un instant...");
        HOLD_C.set("Merci");
        CONNECT_H.set("Attendez s'il vous plaît");
        CONNECT_C.set("Connexion au serveur...");
        LOCAL.set("Local");
        ONLINE.set("En ligne");
        CANCEL.set("Annuler");
        OK.set("OK");
        ACCEPT.set("J'accepte");
        GS_NA_C.set("Merci de revenir plus tard");
        CR_MS_H.set("Impossible d'atteindre le serveur !");
        CR_MS_C.set("Vérifiez votre connexion Internet et choisissez l'une des options suivantes");
        RETRY.set("Réessayez");
        ENTER_IP_PORT.set("Entrez l'IP/PORT du serveur");
        IP_H.set("Entrez une adresse IP valide du serveur");
        PORT_H.set("Entrez un port valide du serveur ");
        WINDOW.set("Fenêtre");
        SWITCH_FS.set("Alterner le mode plein écran");
        SWITCH_THEME.set("Changer de thème");
        LANGUAGE.set("Langue");
        SHORTCUTS.set("Raccourcis");
        GENERAL.set("Général");
        MAIN.set("Menu principale");
        JOIN.set("Rejoindre");
        ROOM.set("Salle");
        GAME.set("Jeu");
        ABOUT.set("À propos");
        HELP.set("Aide");
        ABOUT_ME.set("À propos de moi");
        FEEDBACK.set("Donnez votre avis");
        COPYRIGHT.set("Notice de droits d'auteur");
        DOWNLOAD_UPDATE.set("Téléchargements/mise à jour");
        VIEW_SC.set("Voir le score");
        END_GAME.set("Terminer le jeu");
        GEN_SH_T.set("Raccourcis généraux");
        GEN_SH_H.set("Voici quelques raccourcis généraux");
        GEN_SH_C.set("F1"
                + "\n         Comment jouer"
                + "\n"
                + "\nF2"
                + "\n         Basculer entre le thème clair/sombre"
                + "\n"
                + "\nF11"
                + "\n         Alterner le mode plein écran");
        M_SH_T.set("Raccourcis de menu principale");
        M_SH_H.set("Voici quelques raccourcis à utiliser lorsque vous êtes dans le menu principal");
        M_SH_C.set("Ctrl + H"
                + "\n         Héberger une salle"
                + "\n"
                + "\nCtrl + J"
                + "\n         Rejoindre les salles publiques/locales"
                + "\n"
                + "\nCtrl + O"
                + "\n         Passer en mode en ligne"
                + "\n"
                + "\nCtrl + L"
                + "\n         Passer en mode local"
                + "\n"
                + "\nCtrl + R"
                + "\n         Rejoindre une salle spécifique (mode en ligne uniquement)");
        J_SH_T.set("Raccourcis de menu rejoindre");
        J_SH_H.set("Voici quelques raccourcis à utiliser lorsque vous êtes dans le menu rejoindre");
        J_SH_C.set("Ctrl + PAVÉ NUMÉRIQUE (1-5)"
                + "\n         Rejoignez la salle no."
                + "\n"
                + "\nCtrl + R"
                + "\n         Rafraîchir"
                + "\n"
                + "\nCtrl + N"
                + "\n         Afficher les salles suivantes (mode local uniquement)"
                + "\n"
                + "\nCtrl + H"
                + "\n         Retourner au menu principal");
        R_SH_T.set("Raccourcis des salles");
        R_SH_H1.set("Voici quelques raccourcis à utiliser tant que vous êtes dans une salle"
                + "\n"
                + ", cliquez sur suivant pour plus de raccourcis");
        R_SH_H2.set("Voici quelques raccourcis à utiliser tant que vous êtes dans une salle");
        R_SH_C1.set("Ctrl + C"
                + "\n         Ouvrir le chat (dispo. pendant le jeu)"
                + "\n"
                + "\nCtrl + N"
                + "\n         Changer votre nom"
                + "\n"
                + "\nCtrl + Flèches (Bas, Droite, Haut, Gauche)"
                + "\n         Prendre la place vide"
                + "\n"
                + "\nCtrl + I"
                + "\n         Copier l'id de la salle (mode en ligne uniquement)"
                + "\n"
                + "\nCtrl + R"
                + "\n         Basculer l'état prêt");
        R_SH_C2.set("Ctrl + T"
                + "\n         Afficher/Masquer les notifications"
                + "\n"
                + "\nCtrl + K"
                + "\n         Expulser quelqu'un (administrateurs uniquement) (dispo. pendant le jeu)"
                + "\n"
                + "\nCtrl + P"
                + "\n         Modifier la confidentialité (administrateurs uniquement)"
                + "\n"
                + "\nCtrl + S"
                + "\n         Commencer le jeu (administrateurs uniquement)"
                + "\n"
                + "\nCtrl + H"
                + "\n         Retourner au menu principal");
        NEXT.set("Suivant");
        PREVIOUS.set("Précédent");
        DONE.set("Fait");
        G_SH_T.set("Raccourcis de jeu");
        G_SH_H.set("Voici quelques raccourcis à utiliser pendant le jeu");
        G_SH_C.set("Ctrl + V" +
                "\n         Voir le score"
                + "\n"
                + "\nCtrl + E"
                + "\n         Terminer le jeu (retourner à la salle)");

        UPDATE.set("Mise à jour");
        DOWNLOAD.set("Télécharger");
        CHECK.set("Vérifier");
        DOWNLOAD_NOW.set("Télécharger maintenant");
        DU_XO_H.set("Assurez-vous d'avoir la dernière version de xo"); // todo change in other bg
        DU_XO_C.set("Votre version actuelle est : " + MainApp.CURRENT_VERSION + " vérifier s'il y a une nouvelle version");
        DU_CHECKERS_H.set("Voulez-vous jouer aux dames avec vos amis ?");
        DU_CHECKERS_C.set("Téléchargez mon jeu de dames gratuitement");
        DU_CHESS_H.set("Voulez-vous jouer aux jeu d'échecs avec vos amis ?");
        DU_CHESS_C.set("Téléchargez mon jeu d'échecs gratuitement");
        DU_CONNECT4_H.set("Voulez-vous jouer aux connecte 4 avec vos amis ?");
        DU_CONNECT4_C.set("Téléchargez mon jeu de connecte 4 gratuitement");
        DU_DOMINOS_H.set("Voulez-vous jouer aux dominos avec vos amis ?");
        DU_DOMINOS_C.set("Téléchargez mon jeu de dominos gratuitement");
        DU_COINCHE_H.set("Voulez-vous jouer aux coinche avec vos amis ?");
        DU_COINCHE_C.set("Téléchargez mon jeu de coinche gratuitement");
        ABOUT_T.set("BHA-Bilel envoie ses salutations");
        ABOUT_H.set("Bonjour! je suis BENHADJ AMAR Bilel");
        ABOUT_C.set("Merci d'avoir utiliser mon jeu :)\n" +
                "Trouves-en plus à propos de moi:");
        VISIT_GH.set("Visitez mon profil GitHub");
        VISIT_LI.set("Visitez mon profil LinkedIn");
        HELP_H.set("Vous ne savez pas comment utiliser mon jeu ?");
        HELP_C.set("Découvrez comment jouer");
        HELP_BT.set("J'ai besoin d'aide!");
        FEEDBACK_H.set("Que pensez-vous de ce projet ? laissez vos avis/recommandations !");
        FEEDBACK_C.set("Vous devez avoir un profil GitHub");
        FEEDBACK_BT.set("Donnez votre avis !");
        COPYRIGHT_T.set("Notice de droits d'auteur");
        COPYRIGHT_H.set("Ce jeu contient volontairement du code source SANS LICENCE (PAS open-source),\n"
                + "que je considère seulement comme un projet secondaire personnel et un moyen de mettre en valeur mes compétences.\n"
                + "Vous pouvez sûrement et volontiers jouer à mon jeu, ou voir le code source sur GitHub.");
        COPYRIGHT_C.set("Cependant, JE N'ACCEPTE AUCUN type d'utilisation (commercial, brevet, privé),\n"
                + "distribution ou modification du code source de ce jeu.\n"
                + "\n"
                + "Pour un accord de licence privé, veuillez me contacter à: bilel.bha.pro@gmail.com");
        COPYRIGHT_BT.set("Voir le projet sur GitHub");

        USERNAME.set("Nom d'utilisateur");
        HOST.set("Héberger une salle");
        JOIN_PUB.set("Rejoindre les salles publiques");
        ROOM_ID.set("identifiant de salle");
        JOIN_SPEC.set("Rejoindre la salle");
        UN_H1.set("Le nom d'utilisateur ne peut pas être vide !");
        UN_C1.set("Les noms d'utilisateur valides sont de 1 à 20 caractères");
        UN_H2.set("Nom d'utilisateur trop long !");
        UN_C2.set("Le nom d'utilisateur ne doit pas dépasser 20 caractères");
        RID_H1.set("Identifiant de salle vide !");
        RID_C1.set("Entrez un identifiant de salle pour rejoindre une salle");
        RID_H2.set("Identifiant de salle invalide");
        RID_C2.set("Cette salle n'existe pas !");
        HOST_H.set("Impossible d'héberger une salle");
        CHK_INTERNET.set("Veuillez vérifier votre connexion internet");
        ALL_TAKEN.set("Malheureusement, toutes les salle sont prises."
                + "\n"
                + "Veuillez réessayer plus tard ou utiliser le mode local");
        ROOM_H.set("Impossible de rejoindre la salle");
        ROOM_C.set("Elle est plein ou n'existe pas");
        COMM_ERROR_H.set("Quelque chose s'est mal passé !");
        COMM_ERROR_C.set("Une erreur s'est produite lors de la communication avec le serveur");

        CHAT_T.set("Discuter");
        CHAT_H1.set("Message vide !");
        CHAT_C1.set("Entrez un message à envoyer");
        CHAT_H2.set("Le message que vous avez entré est trop long !");
        CHAT_C2.set("Veuillez ne pas envoyer plus de 50 caractères à la fois");

        GR_H.set("Résultats de partie");
        DRAWS.set("Égalisations : ");

        REFRESH.set("Rafraîchir");
        RET_HOME.set("Retourner au menu principal");
        JOIN_ERROR.set("Une erreur s'est produite lors de la connexion à la salle");
        GONE_PRIVATE.set("La salle est devenue privée");
        NO_ROOMS.set("Aucune chambre n'est disponible pour le moment,\nVeuillez réessayer plus tard.");
        PLAYERS.set(" joueurs");
        OPEN_CH.set("Ouvrir le chat");
        CH_NAME.set("Changer votre nom");
        NAME_H.set("Vous avez entré le même nom !");
        NAME_C.set("Vous avez déjà ce nom");
        ENTER_UN.set("Entrez un nom d'utilisateur valide");
        TAKE_PLACE.set("Prendre la place");
        COPY.set("Copier");
        NOTIFICATIONS.set("Notifications");

        HOST_PRIV.set("Privilèges d'hôte");
        ROOM_PR.set("Confidentialité de salle");
        PRIVATE.set("Privée");
        PUBLIC.set("Publique");
        KICK_PLAYER.set("Expulser un membre");
        START_GAME.set("Commencer le jeu");

        KICK_H1.set("Tu ne peux expulser personne");
        KICK_C1.set("Vous êtes la seul personne dans la salle !");
        KICK_H2.set("Sélectionnez le joueur que vous voulez expulser de la salle");
        KICK_C2.set("Il pourrait toujours rejoindre la salle");

        JOINED_NOTIF.set(" a rejoint la salle");
        LEFT_NOTIF.set(" a quitté la salle");
        MIGRATION_H1.set("Migration a commencé");
        MIGRATION_C1.set("Veuillez attendre la migration vers une nouvelle salle");
        MIGRATION_H2.set("Migration terminée");
        MIGRATION_C2.set("Vous êtes maintenant connecté à la  salle de nouveau hôte");
        CNT_ACCESS_H.set("Vous ne pouvez pas accéder à cette salle");
        CNT_ACCESS_C.set("La pièce à laquelle vous essayez d'accéder est destinée à un autre jeu");
        GE_H.set("Le jeu est terminé !");
        GE_C.set("Un événement a conduit le jeu à se terminer");
        CNT_SG_H.set("Vous ne pouvez pas encore commencer le jeu !");
        CNT_SG_C1.set("Tous les joueurs doivent être prêts à commencer le jeu");
        CNT_SG_C2.set("Veuillez attendre que votre adversaire vous rejoigne"); // todo change in coinche and dominoes
        READY.set("Prêt");
    }

    private static void load_english_strings() {
        COINCHE.set("Coinche");
        DOMINOS.set("Dominoes");
        CONNECT4.set("Connect 4");
        XO.set("xo");
        CHESS.set("Chess");
        CHECKERS.set("Checkers");
        SKIP_H.set("Do you really want to skip the tutorial?");
        SKIP_C.set("This is a one time experience, please complete it for your interest,"
                + "\n"
                + " it won't take long!");
        GO_BACK.set("Continue tutorial");
        SKIP_BT.set("Skip tutorial");
        LANG_H.set("Welcome!");
        LANG_C.set("Please choose your preferred language to proceed, this can always be changed later");
        THEME_H.set("what language do you prefer?");
        THEME_C.set("Choose between light and dark mode, this can always be changed later");
        LIGHT.set("Light");
        DARK.set("Dark");
        WELCOME_H.set("We're almost done! let me introduce you to my game");
        WELCOME_C.set("Hi! I am BENHADJ AMAR Bilel, the developer of this game"
                + "\n"
                + "\n"
                + "My xo game is no ordinary game!" // todo change in other bg
                + "\n"
                + "It comes in two themes, and three languages"
                + "\n"
                + "It offers real multiplayer, no AI included, only real people"
                + "\n"
                + "It can be played locally in a local area network, or online through Internet"
                + "\n"
                + "It contains a built-in chat app so you can make new friends, and stay connected while playing"
                + "\n"
                + "\n"
                + "For the best experience, don't forget to check for updates from time to time"
                + "\n");
        WELCOME_BACK.set("Welcome back! :)");
        SHORTCUTS_H.set("Now you're ready!");
        SHORTCUTS_C.set("Thank you for your patience, you can now start playing the game, enjoy ;)");
        HOLD_H.set("Please hold for a moment...");
        HOLD_C.set("Thank you");
        CONNECT_H.set("Please wait");
        CONNECT_C.set("Connecting to server...");
        LOCAL.set("Local");
        ONLINE.set("Online");
        CANCEL.set("Cancel");
        OK.set("OK");
        ACCEPT.set("I accept");
        GS_NA_C.set("Please come back later");
        CR_MS_H.set("Couldn't reach the server!");
        CR_MS_C.set("Check your Internet connection, and choose one of the following");
        RETRY.set("Retry");
        ENTER_IP_PORT.set("Enter server ip/port");
        IP_H.set("Enter a valid server ip");
        PORT_H.set("Enter a valid server port");
        WINDOW.set("Window");
        SWITCH_FS.set("Toggle fullscreen mode");
        SWITCH_THEME.set("Switch theme");
        LANGUAGE.set("Language");
        SHORTCUTS.set("Shortcuts");
        GENERAL.set("General");
        MAIN.set("Main");
        JOIN.set("Join");
        ROOM.set("Room");
        GAME.set("Game");
        ABOUT.set("About");
        HELP.set("Help");
        ABOUT_ME.set("About me");
        FEEDBACK.set("Give feedback");
        COPYRIGHT.set("Copyright notice");
        DOWNLOAD_UPDATE.set("Download/Update");
        VIEW_SC.set("View score");
        END_GAME.set("End game");
        GEN_SH_T.set("General Shortcuts");
        GEN_SH_H.set("Here are some general shortcuts");
        GEN_SH_C.set("F1"
                + "\n         How to play"
                + "\n"
                + "\nF2"
                + "\n         Switch between light/dark theme"
                + "\n"
                + "\nF11"
                + "\n         Toggle fullscreen mode");
        M_SH_T.set("Main Shortcuts");
        M_SH_H.set("Here are some shortcuts to use while you're in main menu");
        M_SH_C.set("Ctrl + H"
                + "\n         Host a room"
                + "\n"
                + "\nCtrl + J"
                + "\n         Join public/local rooms"
                + "\n"
                + "\nCtrl + O"
                + "\n         Switch to online mode"
                + "\n"
                + "\nCtrl + L"
                + "\n         Switch to local mode"
                + "\n"
                + "\nCtrl + R"
                + "\n         Join specific room (online mode only)");
        J_SH_T.set("Join Shortcuts");
        J_SH_H.set("Here are some shortcuts to use while you're in join menu");
        J_SH_C.set("Ctrl + NUMPAD (1-5)"
                + "\n         Join room no."
                + "\n"
                + "\nCtrl + R"
                + "\n         Refresh"
                + "\n"
                + "\nCtrl + N"
                + "\n         Show next rooms (local mode only)"
                + "\n"
                + "\nCtrl + H"
                + "\n         Return home");
        R_SH_T.set("Room Shortcuts");
        R_SH_H1.set("Here are some shortcuts to use while you're in a room"
                + "\n"
                + ", click next for more");
        R_SH_H2.set("Here are some shortcuts to use while you're in a room");
        R_SH_C1.set("Ctrl + C"
                + "\n         Open chat (also available in-game)"
                + "\n"
                + "\nCtrl + N"
                + "\n         Change name"
                + "\n"
                + "\nCtrl + Arrow keys (Bottom, Right, Up, Left)"
                + "\n         Take empty place"
                + "\n"
                + "\nCtrl + I"
                + "\n         Copy room id (online mode only)"
                + "\n"
                + "\nCtrl + R"
                + "\n         Toggle ready status");
        R_SH_C2.set("Ctrl + T"
                + "\n         Show/Suppress notifications"
                + "\n"
                + "\nCtrl + K"
                + "\n         Kick someone (for admins only) (also available in-game)"
                + "\n"
                + "\nCtrl + P"
                + "\n         Change privacy (for admins only)"
                + "\n"
                + "\nCtrl + S"
                + "\n         Start game (for admins only)"
                + "\n"
                + "\nCtrl + H"
                + "\n         Return home");
        NEXT.set("Next");
        PREVIOUS.set("Previous");
        DONE.set("Done");
        G_SH_T.set("Game Shortcuts");
        G_SH_H.set("Here are some shortcuts to use while playing the game");
        G_SH_C.set("Ctrl + V" +
                "\n         View score"
                + "\n"
                + "\nCtrl + E"
                + "\n         End game (return to room)");

        UPDATE.set("Update");
        DOWNLOAD.set("Download");
        CHECK.set("Check");
        DOWNLOAD_NOW.set("Download now");
        DU_XO_H.set("Make sure you have the latest version of xo"); // todo change in other bg
        DU_XO_C.set("Your current version is : " + MainApp.CURRENT_VERSION + ", Check for new version");
        DU_CHECKERS_H.set("Want to play Checkers with your friends?");
        DU_CHECKERS_C.set("Download my Checkers app for free");
        DU_CHESS_H.set("Want to play Chess with your friends?");
        DU_CHESS_C.set("Download my Chess app for free");
        DU_CONNECT4_H.set("Want to play Connect 4 with your friends?");
        DU_CONNECT4_C.set("Download my Connect 4 app for free");
        DU_DOMINOS_H.set("Want to play Dominoes with your friends?");
        DU_DOMINOS_C.set("Download my Dominoes app for free");
        DU_COINCHE_H.set("Want to play Coinche with your friends?");
        DU_COINCHE_C.set("Download my Coinche app for free");
        ABOUT_T.set("BHA-Bilel sends his regards");
        ABOUT_H.set("Hello! I am BENHADJ AMAR Bilel");
        ABOUT_C.set("Thanks for playing my game :)\n" +
                "Find out more about me:");
        VISIT_GH.set("Visit GitHub profile");
        VISIT_LI.set("Visit LinkedIn profile");
        HELP_H.set("You don't know how to use my app?");
        HELP_C.set("Check out how to play");
        HELP_BT.set("I need help!");
        FEEDBACK_H.set("What do you think of this project? leave your thoughts/recommendations!");
        FEEDBACK_C.set("You need to have a GitHub profile");
        FEEDBACK_BT.set("Give feedback!");
        COPYRIGHT_T.set("Copyright notice");
        COPYRIGHT_H.set("This game contain purposely UNLICENSED source code (NOT open-source),\n"
                + "that I only consider as a personal side project and a way to showcase my skills.\n"
                + "You can surely and gladly play my game, or view how it's made on GitHub.");
        COPYRIGHT_C.set("However, I DO NOT grant any kind of usage (commercial, patent, private),\n"
                + "distribution or modification of the source code of this game.\n"
                + "\n"
                + "For a private license agreement please contact me at: bilel.bha.pro@gmail.com");
        COPYRIGHT_BT.set("See project on GitHub");

        USERNAME.set("Username");
        HOST.set("Host a room");
        JOIN_PUB.set("Join public rooms");
        ROOM_ID.set("Room ID");
        JOIN_SPEC.set("Join room");
        UN_H1.set("Username can't be empty!");
        UN_C1.set("Valid usernames are from 1 to 20 characters");
        UN_H2.set("Username too long!");
        UN_C2.set("Username must not exceed 20 characters");
        RID_H1.set("Empty room ID!");
        RID_C1.set("Enter a room ID to join a room");
        RID_H2.set("Invalid room ID");
        RID_C2.set("This room doesn't exist!");
        HOST_H.set("Couldn't host a room");
        CHK_INTERNET.set("Please check your Internet connection");
        ALL_TAKEN.set("Unfortunately, all rooms are taken."
                + "\n"
                + "Please try again later, or switch to local mode");
        ROOM_H.set("Couldn't join the room");
        ROOM_C.set("It's either full or doesn't exist");
        COMM_ERROR_H.set("Something went wrong!");
        COMM_ERROR_C.set("An error has occurred while communicating with the server");

        CHAT_T.set("Chat");
        CHAT_H1.set("Empty message!");
        CHAT_C1.set("Enter a message to send");
        CHAT_H2.set("The message you entered is too long!");
        CHAT_C2.set("Please send no more than 50 characters at a time");

        GR_H.set("Game results");
        DRAWS.set("Draws : ");

        REFRESH.set("Refresh");
        RET_HOME.set("Return Home");
        JOIN_ERROR.set("An error has occurred when joining the room");
        GONE_PRIVATE.set("This room has gone private");
        NO_ROOMS.set("No rooms are available at the moment,\nPlease come back later.");
        PLAYERS.set(" players");
        OPEN_CH.set("Open chat");
        CH_NAME.set("Change name");
        NAME_H.set("You entered the same name!");
        NAME_C.set("You already have that name");
        ENTER_UN.set("Enter a valid username");
        TAKE_PLACE.set("Take place");
        COPY.set("Copy");
        NOTIFICATIONS.set("Notifications");

        HOST_PRIV.set("Host Privileges");
        ROOM_PR.set("Room Privacy");
        PRIVATE.set("Private");
        PUBLIC.set("Public");
        KICK_PLAYER.set("Kick a player");
        START_GAME.set("Start game");

        KICK_H1.set("You can't kick anyone");
        KICK_C1.set("You're the only one in the room!");
        KICK_H2.set("Select the player you want to kick from the room");
        KICK_C2.set("He could still rejoin the room");

        JOINED_NOTIF.set(" joined the room");
        LEFT_NOTIF.set(" left the room");
        MIGRATION_H1.set("Migration started");
        MIGRATION_C1.set("Please wait for migration to new room");
        MIGRATION_H2.set("Migration finished");
        MIGRATION_C2.set("You are now connected to new host's room");
        CNT_ACCESS_H.set("You can't access this room");
        CNT_ACCESS_C.set("The room you're trying to access is meant for another game");
        GE_H.set("The game has ended!");
        GE_C.set("An event has led the game to finish");
        CNT_SG_H.set("You can't start the game yet!");
        CNT_SG_C1.set("All players must be ready to start the game");
        CNT_SG_C2.set("Please wait for your opponent to join you");
        READY.set("Ready");
    }

    public static void first_timer() {
        init();
        String lang = System.getProperty("user.language");
        switch (lang) {
            case "fr":
                load_lang(LANGNAME.FRENCH);
                break;
            case "ar":
                load_lang(LANGNAME.ARABIC);
                break;
            default:
                load_lang(LANGNAME.ENGLISH);
        }
    }

}
