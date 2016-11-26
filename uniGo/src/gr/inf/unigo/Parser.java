package gr.inf.unigo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Parser
{
    private final String USER_AGENT = "Mozilla/5.0";
    private List<String> cookies;
    private HttpsURLConnection conn;
    public String grades;

    public String getWeeklySchedule()
    {
        StringBuilder html = new StringBuilder();
        html.append( "\n" +
                "<!DOCTYPE html>\n" +
                "<html lang=\"el\">\n" +
                "<head>\n" +
                "</head>\n" +
                "\n" +
                "<!-- Begin Body -->\n" +
                "<body class=\"page page-id-1282 page-child parent-pageid-1336 page-template-default logged-in no-font-smoothing mega-menu-main-menu\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div id=\"wrap\" class=\"clearfix shadow-container\">\n" +
                "\n" +
                "    <div class=\"modal fade\" id=\"ece-login-modal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n" +
                "        <div class=\"modal-dialog\">\n" +
                "            <div class=\"modal-content\">\n" +
                "                <div class=\"modal-header\">\n" +
                "\t\t\t<a class=\"close fa fa-times-circle alignright\" data-dismiss=\"modal\" ></a>\n" +
                "\t\t\t<img src=\"https://www.e-ce.uth.gr/wp-content/uploads/theme_logos/login_logo_gr.png\" width=\"568\" height=\"53\" />\n" +
                "                </div>\n" +
                "                <div class=\"modal-body\">\n" +
                "\t\t<form name=\"loginform\" id=\"loginform\" action=\"https://www.e-ce.uth.gr/wp-login.php\" method=\"post\">\n" +
                "\t\t\t<div class=\"input\">\n" +
                "\t\t\t\t<input type=\"text\" name=\"log\" id=\"user_login\" placeholder=\"Όνομα χρήστη\" required />\n" +
                "\t\t\t\t<span><i class=\"fa fa-user\"></i></span>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div class=\"input\">\n" +
                "\t\t\t<input type=\"password\" name=\"pwd\" id=\"user_pass\" placeholder=\"Συνθηματικό\" required />\n" +
                "\t\t\t<span><i class=\"fa fa-lock\"></i></span>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<p class=\"login-submit\">\n" +
                "\t\t\t<button type=\"submit\" name=\"wp-submit\" id=\"wp-submit\" class=\"submit\"><i class=\"fa fa-long-arrow-right\"></i></button>\n" +
                "\t\t\t<input type=\"hidden\" name=\"redirect_to\" value=\"https://www.e-ce.uth.gr/studies/undergraduate/fall-timetable/week/\" />\n" +
                "\t\t\t</p>\n" +
                "\t\t</form>\n" +
                "\t\t</div>\n" +
                "            </div><!-- /.modal-content -->\n" +
                "        </div><!-- /.modal-dialog -->\n" +
                "    </div><!-- /.modal -->\n" +
                "\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t\t\n" +
                "\t<!-- nfragog - Disabled for MegaMaxMenu\n" +
                "\t<nav id=\"navigation\" class=\"clearfix\">  --> \n" +
                "\t\t<div id=\"mega-menu-wrap-main_menu\" class=\"mega-menu-wrap\"><div class=\"mega-menu-toggle\" tabindex=\"0\"><div class='mega-toggle-block mega-menu-toggle-block mega-toggle-block-right mega-toggle-block-1' id='mega-toggle-block-1'></div></div><ul id=\"mega-menu-main_menu\" class=\"mega-menu mega-menu-horizontal mega-no-js\" data-event=\"hover_intent\" data-effect=\"fade_up\" data-effect-speed=\"200\" data-second-click=\"close\" data-document-click=\"collapse\" data-vertical-behaviour=\"standard\" data-breakpoint=\"640\"><li class='mega-menu-item mega-menu-item-type-custom mega-menu-item-object-custom mega-menu-item-has-children mega-align-bottom-right mega-menu-megamenu mega-menu-item-2934' id='mega-menu-item-2934'><a class=\"fa-building-o mega-menu-link\" href=\"#\" aria-haspopup=\"true\" tabindex=\"0\">Τμήμα</a>\n" +
                "<ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-item-2749' id='mega-menu-item-2749'><a class=\"fa-safari mega-menu-link\" href=\"https://www.e-ce.uth.gr/department/profile/\">Φυσιογνωμία</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-item-168' id='mega-menu-item-168'><a class=\"fa-info mega-menu-link\" href=\"https://www.e-ce.uth.gr/department/information/\">Διοίκηση – Πληροφορίες</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-item-167' id='mega-menu-item-167'><a class=\"fa-briefcase mega-menu-link\" href=\"https://www.e-ce.uth.gr/department/faculty/\">Διδάσκοντες</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-clear mega-menu-item-166' id='mega-menu-item-166'><a class=\"fa-users mega-menu-link\" href=\"https://www.e-ce.uth.gr/department/staff/\">Προσωπικό</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-item-162' id='mega-menu-item-162'><a class=\"dashicons-format-aside mega-menu-link\" href=\"https://www.e-ce.uth.gr/department/programmes/\">Προγράμματα</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-item-16224' id='mega-menu-item-16224'><a class=\"fa-tachometer mega-menu-link\" href=\"https://www.e-ce.uth.gr/department/internal-review/\">Αξιολόγηση Τμήματος</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-clear mega-menu-item-11771' id='mega-menu-item-11771'><a class=\"fa-video-camera mega-menu-link\" href=\"https://www.e-ce.uth.gr/department/ece-building-construction-webcam/\">Κάμερα στο Εργοτάξιο του Νέου Κτηρίου</a></li></ul>\n" +
                "</li><li class='mega-menu-item mega-menu-item-type-custom mega-menu-item-object-custom mega-current-menu-ancestor mega-menu-item-has-children mega-align-bottom-left mega-menu-tabbed mega-menu-megamenu mega-menu-item-2933' id='mega-menu-item-2933'><a class=\"fa-book mega-menu-link\" href=\"#\" aria-haspopup=\"true\" tabindex=\"0\">Σπουδές</a>\n" +
                "<ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-item-2745' id='mega-menu-item-2745'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/subject-areas/\">Γνωστικά Αντικείμενα</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-current-page-ancestor mega-menu-item-160' id='mega-menu-item-160'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/undergraduate/\">Προπτυχιακές Σπουδές</a></li><li class='mega-menu-item mega-menu-item-type-custom mega-menu-item-object-custom mega-menu-item-has-children mega-disable-link mega-menu-item-3406' id='mega-menu-item-3406'><a class=\"mega-menu-link\" aria-haspopup=\"true\">Μεταπτυχιακές Σπουδές</a>\n" +
                "\t<ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-6 mega-menu-item-3407' id='mega-menu-item-3407'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/postgraduate/science-and-technology-of-ece/\">Μ.Δ.Ε. στην «Επιστήμη και Τεχνολογία ΗΜΜΥ»</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-6 mega-menu-item-14287' id='mega-menu-item-14287'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/postgraduate/applied-informatics/\">Μ.Δ.Ε. στην «Εφαρμοσμένη Πληροφορική»</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-6 mega-menu-item-11078' id='mega-menu-item-11078'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/postgraduate/science-and-technology-of-ece/msc-scholarships/\">Υπότροφοι Π.Μ.Σ. «Επιστήμη και Τεχνολογία ΗΜΜΥ»</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-6 mega-menu-item-19029' id='mega-menu-item-19029'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/postgraduate/applied-informatics/msc-scholarships/\">Υπότροφοι Π.Μ.Σ. «Εφαρμοσμένη Πληροφορική»</a></li>\t</ul>\n" +
                "</li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-item-3408' id='mega-menu-item-3408'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/postgraduate/phd-studies/\">Διδακτορικές Σπουδές</a></li><li class='mega-menu-item mega-menu-item-type-custom mega-menu-item-object-custom mega-menu-item-has-children mega-disable-link mega-menu-item-2959' id='mega-menu-item-2959'><a class=\"mega-menu-link\" aria-haspopup=\"true\">Κατάλογος Μαθημάτων</a>\n" +
                "\t<ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-6 mega-menu-item-7835' id='mega-menu-item-7835'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/undergraduate/courses/\">Προπτυχιακά Μαθήματα</a></li><li class='mega-menu-item mega-menu-item-type-custom mega-menu-item-object-custom mega-menu-item-has-children mega-disable-link mega-menu-columns-1-of-6 mega-menu-item-14462' id='mega-menu-item-14462'><a class=\"mega-menu-link\" aria-haspopup=\"true\">Μεταπτυχιακά Μαθήματα</a>\n" +
                "\t\t<ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-item-7916' id='mega-menu-item-7916'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/postgraduate/science-and-technology-of-ece/courses/\">Επιστήμη και Τεχνολογία ΗΜΜΥ</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-item-14463' id='mega-menu-item-14463'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/postgraduate/applied-informatics/courses/\">Εφαρμοσμένη Πληροφορική</a></li>\t\t</ul>\n" +
                "</li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-6 mega-menu-item-520' id='mega-menu-item-520'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/undergraduate/erasmus/\">Erasmus</a></li>\t</ul>\n" +
                "</li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-item-12367' id='mega-menu-item-12367'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/ects/\">Πιστωτικές Μονάδες ECTS</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-item-7124' id='mega-menu-item-7124'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/academic-calendar/\">Ακαδημαϊκό Ημερολόγιο</a></li><li class='mega-menu-item mega-menu-item-type-custom mega-menu-item-object-custom mega-current-menu-ancestor mega-current-menu-parent mega-menu-item-has-children mega-disable-link mega-menu-item-17326' id='mega-menu-item-17326'><a class=\"mega-menu-link\" aria-haspopup=\"true\">Ωρολόγιο Π.Π.Σ. Χειμερινού</a>\n" +
                "\t<ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-current-menu-item mega-page_item mega-page-item-1282 mega-current_page_item mega-menu-columns-1-of-6 mega-menu-item-18537' id='mega-menu-item-18537'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/undergraduate/fall-timetable/week/\">Εβδομαδιαίο Ωρολόγιο Π.Π.Σ. Χειμερινού Εξαμήνου</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-6 mega-menu-item-18535' id='mega-menu-item-18535'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/undergraduate/fall-timetable/year/\">Ανα Έτος Ωρολόγιο Π.Π.Σ. Χειμερινού</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-6 mega-menu-item-18536' id='mega-menu-item-18536'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/undergraduate/fall-timetable/courses-taught/\">Μαθήματα Π.Π.Σ. Χειμερινού που διδάσκονται τώρα</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-hide-on-desktop mega-hide-on-mobile mega-menu-columns-1-of-6 mega-menu-item-16552' id='mega-menu-item-16552'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/undergraduate/spring-timetable/week/\">Εβδομαδιαίο Ωρολόγιο Π.Π.Σ. Εαρινού Εξαμήνου</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-hide-on-desktop mega-hide-on-mobile mega-menu-columns-1-of-6 mega-menu-item-16553' id='mega-menu-item-16553'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/undergraduate/spring-timetable/year/\">Ανα Έτος Ωρολόγιο Π.Π.Σ. Εαρινού</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-hide-on-desktop mega-hide-on-mobile mega-menu-columns-1-of-6 mega-menu-item-16551' id='mega-menu-item-16551'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/undergraduate/spring-timetable/courses-taught/\">Μαθήματα Π.Π.Σ. Εαρινού που διδάσκονται τώρα</a></li>\t</ul>\n" +
                "</li><li class='mega-menu-item mega-menu-item-type-custom mega-menu-item-object-custom mega-menu-item-has-children mega-disable-link mega-menu-item-17328' id='mega-menu-item-17328'><a class=\"mega-menu-link\" aria-haspopup=\"true\">Πρόγραμμα Εξεταστικής</a>\n" +
                "\t<ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item mega-menu-item-type-taxonomy mega-menu-item-object-category mega-menu-columns-1-of-6 mega-menu-item-3972' id='mega-menu-item-3972'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/category/exams-schedule/undergraduate-studies/\">Εξεταστική Π.Π.Σ.</a></li>\t</ul>\n" +
                "</li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-item-157' id='mega-menu-item-157'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/career-opportunities/\">Επαγγελματικά Θέματα</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-item-159' id='mega-menu-item-159'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/studies/practise-training/\">Πρακτική Ασκηση</a></li></ul>\n" +
                "</li><li class='mega-menu-item mega-menu-item-type-custom mega-menu-item-object-custom mega-menu-item-has-children mega-align-bottom-left mega-menu-megamenu mega-menu-item-2935' id='mega-menu-item-2935'><a class=\"fa-laptop mega-menu-link\" href=\"#\" aria-haspopup=\"true\" tabindex=\"0\">Έρευνα</a>\n" +
                "<ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-item-4538' id='mega-menu-item-4538'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/research/labs/\">Εργαστήρια</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-item-7720' id='mega-menu-item-7720'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/research/projects/\">Ερευνητικά Προγράμματα</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-item-7079' id='mega-menu-item-7079'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/research/postdoc-research/\">Μεταδιδακτορική Έρευνα</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-clear mega-menu-item-1858' id='mega-menu-item-1858'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/research/phd-candidates/\">Υποψήφιοι Διδάκτορες</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-item-7688' id='mega-menu-item-7688'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/research/theses-technical_reports/\">Διατριβές – Εργασίες</a></li><li class='mega-menu-item mega-menu-item-type-widget widget_text mega-menu-columns-3-of-3 mega-menu-clear mega-menu-item-text-4' id='mega-menu-item-text-4'>\t\t\t<div class=\"textwidget\"><div class=\"roundbox\" style=\"margin: 5px 0px 13px 0px;\">\n" +
                "<h4 class=\"mega-block-title\">Ερευνητικά Προγράμματα σε Εξέλιξη</h4>\n" +
                "<div class=\"toggle-wrap\">\n" +
                "<h3 class=\"trigger\"><a href=\"#\">SCoRPiO- Significance-Based Computing for Reliability and Power Optimization</a></h3>\n" +
                "<div class=\"toggle_container\" style=\"display: none;\">\n" +
                "<div style=\"overflow-x:auto;\">\n" +
                "<table class=\"course\">\n" +
                "<tr>\n" +
                "<th>Επιστ. Υπεύθυνος</th>\n" +
                "<td class=\"first\">\n" +
                "<p style=\"clear:both;\"><img style=\"float: left; margin: 0px 6px 0px 0px;\" src=\"https://www.e-ce.uth.gr/wp-content/uploads/faculty_logos/kent.jpg\" class=\"img-circle\" width=\"60px\" alt=\"\" /><a href=\"https://www.e-ce.uth.gr/?p=1364\">Μπέλλας Νικόλαος</a>, Αναπληρωτής Καθηγητής<br />\n" +
                "E-mail: <a href=\"mailto:nbellas@e-ce.uth.gr\">nbellas@e-ce.uth.gr</a></p>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<th>Τίτλος</th>\n" +
                "<td>SCoRPiO- Significance-Based Computing for Reliability and Power Optimization</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<th>Διάρκεια</th>\n" +
                "<td>2013 &#8211; 2016</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<th>Ιστοσελίδα</th>\n" +
                "<td><a href=\"http://www.scorpio-project.eu\" rel=\"nofollow\">http://www.scorpio-project.eu</a></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "<p style=\"padding-top: 12px; text-align: right;\"><a title=\"SCoRPiO- Significance-Based Computing for Reliability and Power Optimization\" href=\"https://www.e-ce.uth.gr/research/projects/scorpio-significance-based-computing-for-reliability-and-power-optimization/\">Περισσότερες Πληροφορίες</a></p>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div class=\"toggle-wrap\">\n" +
                "<h3 class=\"trigger\"><a href=\"#\">Fed4FIRE &#8211; Federation for FIRE</a></h3>\n" +
                "<div class=\"toggle_container\" style=\"display: none;\">\n" +
                "<div style=\"overflow-x:auto;\">\n" +
                "<table class=\"course\">\n" +
                "<tr>\n" +
                "<th>Επιστ. Υπεύθυνος</th>\n" +
                "<td class=\"first\">\n" +
                "<p style=\"clear:both;\"><img style=\"float: left; margin: 0px 6px 0px 0px;\" src=\"https://www.e-ce.uth.gr/wp-content/uploads/formidable/tasioulas2.jpg\" class=\"img-circle\" width=\"60px\" alt=\"Τασιούλας Λέανδρος\"/><a href=\"https://www.e-ce.uth.gr/?p=1350\">Τασιούλας Λέανδρος</a>, Καθηγητής<br />\n" +
                "E-mail: <a href=\"mailto:leandros@e-ce.uth.gr\">leandros@e-ce.uth.gr</a></p>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<th>Τίτλος</th>\n" +
                "<td>Fed4FIRE &#8211; Federation for FIRE</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<th>Διάρκεια</th>\n" +
                "<td>2012 &#8211; 2016</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<th>Ιστοσελίδα</th>\n" +
                "<td><a href=\"http://www.fed4fire.eu/home.html\" rel=\"nofollow\">http://www.fed4fire.eu/home.html</a></td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "<p style=\"padding-top: 12px; text-align: right;\"><a title=\"Fed4FIRE - Federation for FIRE\" href=\"https://www.e-ce.uth.gr/research/projects/fed4fire-federation-for-fire/\">Περισσότερες Πληροφορίες</a></p>\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "</div></div>\n" +
                "\t\t</li></ul>\n" +
                "</li><li class='mega-menu-item mega-menu-item-type-custom mega-menu-item-object-custom mega-menu-item-has-children mega-align-bottom-left mega-menu-megamenu mega-menu-item-2936' id='mega-menu-item-2936'><a class=\"dashicons-welcome-widgets-menus mega-menu-link\" href=\"#\" aria-haspopup=\"true\" tabindex=\"0\">Δραστηριότητες</a>\n" +
                "<ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-item-2862' id='mega-menu-item-2862'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/activities/lectures/\">Διαλέξεις</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-item-11919' id='mega-menu-item-11919'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/activities/pgs-seminars/\">Σεμινάρια Π.Μ.Σ</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-item-2861' id='mega-menu-item-2861'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/activities/conferences/\">Ημερίδες & Συνέδρια</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-3 mega-menu-clear mega-menu-item-8592' id='mega-menu-item-8592'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/activities/calendar/\">Ημερολόγιο Εκδηλώσεων</a></li></ul>\n" +
                "</li><li class='mega-menu-item mega-menu-item-type-custom mega-menu-item-object-custom mega-menu-item-has-children mega-align-bottom-left mega-menu-megamenu mega-menu-item-7610' id='mega-menu-item-7610'><a class=\"fa-graduation-cap mega-menu-link\" href=\"#\" aria-haspopup=\"true\" tabindex=\"0\">Απόφοιτοι</a>\n" +
                "<ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item mega-menu-item-type-widget widget_text mega-menu-columns-1-of-2 mega-menu-item-text-7' id='mega-menu-item-text-7'>\t\t\t<div class=\"textwidget\"><ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item'><a class=\"fa-trophy mega-menu-link\" style=\"font-size:16px; padding: 0px 0px 0px 0px; margin-bottom: 5px;\" href=\"https://www.e-ce.uth.gr/alumni/equality/\">Ισοτιμία ΜΗΥΤΔ με ΗΜΜΥ</a></li><li class='mega-menu-item'><a class=\"fa-commenting-o mega-menu-link\" style=\"font-size:16px; padding: 0px 0px 0px 0px; margin-bottom: 5px;\" href=\"https://www.e-ce.uth.gr/alumni/student-testimonials/\">Γνώμες Αποφοίτων</a></li><li class='mega-menu-item'><a class=\"dashicons-welcome-learn-more mega-menu-link\" style=\"font-size:16px; padding: 0px 0px 0px 0px;\" href=\"https://www.e-ce.uth.gr/alumni/phd-graduates/\">Διδάκτορες</a></li></ul></div>\n" +
                "\t\t</li><li class='mega-menu-item mega-menu-item-type-widget widget_text mega-menu-columns-1-of-2 mega-menu-item-text-6' id='mega-menu-item-text-6'>\t\t\t<div class=\"textwidget\"><img src=\"https://www.e-ce.uth.gr/wp-content/themes/office/images/alumni.jpg\" width=\"490\" height=\"276\"></img></div>\n" +
                "\t\t</li></ul>\n" +
                "</li><li class='mega-menu-item mega-menu-item-type-custom mega-menu-item-object-custom mega-menu-item-has-children mega-align-bottom-left mega-menu-tabbed mega-menu-megamenu mega-menu-item-2940' id='mega-menu-item-2940'><a class=\"fa-cog mega-menu-link\" href=\"#\" aria-haspopup=\"true\" tabindex=\"0\">Υπηρεσίες</a>\n" +
                "<ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item mega-menu-item-type-custom mega-menu-item-object-custom mega-menu-item-has-children mega-menu-item-2954' id='mega-menu-item-2954'><a class=\"fa-info-circle mega-menu-link\" href=\"#\" aria-haspopup=\"true\">Γραμματεία</a>\n" +
                "\t<ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-6 mega-menu-item-2955' id='mega-menu-item-2955'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/secretariat/info/\">Πληροφορίες</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-6 mega-menu-item-2966' id='mega-menu-item-2966'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/secretariat/general-applications/\">Γενικά Έντυπα</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-6 mega-menu-item-2982' id='mega-menu-item-2982'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/secretariat/undergraduate-applications/\">Αιτήσεις Προπτυχιακών</a></li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-columns-1-of-6 mega-menu-item-2971' id='mega-menu-item-2971'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/secretariat/e-university/\">e-Γραμματεία</a></li>\t</ul>\n" +
                "</li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-item-163' id='mega-menu-item-163'><a class=\"fa-sitemap mega-menu-link\" href=\"https://www.e-ce.uth.gr/technical-support/\">Τεχνική Υποστήριξη</a></li></ul>\n" +
                "</li><li class='mega-menu-item mega-menu-item-type-custom mega-menu-item-object-custom mega-menu-item-has-children mega-align-bottom-left mega-menu-megamenu mega-menu-item-2932' id='mega-menu-item-2932'><a class=\"dashicons-megaphone mega-menu-link\" href=\"#\" aria-haspopup=\"true\" tabindex=\"0\">Ανακοινώσεις</a>\n" +
                "<ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item mega-menu-item-type-taxonomy mega-menu-item-object-category mega-menu-columns-1-of-4 mega-menu-item-1124' id='mega-menu-item-1124'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/category/announcements/general-announcements/\">Γενικές Ανακοινώσεις</a></li><li class='mega-menu-item mega-menu-item-type-taxonomy mega-menu-item-object-category mega-menu-columns-1-of-4 mega-menu-item-4780' id='mega-menu-item-4780'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/category/announcements/academic-news/\">Ακαδημαϊκά Νέα</a></li><li class='mega-menu-item mega-menu-item-type-taxonomy mega-menu-item-object-category mega-menu-columns-1-of-4 mega-menu-item-1129' id='mega-menu-item-1129'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/category/announcements/undergraduates/\">Προπτυχιακών</a></li><li class='mega-menu-item mega-menu-item-type-taxonomy mega-menu-item-object-category mega-menu-columns-1-of-4 mega-menu-item-9065' id='mega-menu-item-9065'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/category/announcements/practise-training/\">Πρακτική Άσκηση</a></li><li class='mega-menu-item mega-menu-item-type-taxonomy mega-menu-item-object-category mega-menu-columns-1-of-4 mega-menu-clear mega-menu-item-15033' id='mega-menu-item-15033'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/category/announcements/alumni/\">Αποφοίτων</a></li><li class='mega-menu-item mega-menu-item-type-taxonomy mega-menu-item-object-category mega-menu-columns-1-of-4 mega-menu-item-5453' id='mega-menu-item-5453'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/category/announcements/positions/\">Θέσεις Εργασίας</a></li><li class='mega-menu-item mega-menu-item-type-taxonomy mega-menu-item-object-category mega-menu-columns-1-of-4 mega-menu-item-1125' id='mega-menu-item-1125'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/category/announcements/events/\">Εκδηλώσεις</a></li><li class='mega-menu-item mega-menu-item-type-taxonomy mega-menu-item-object-category mega-menu-columns-1-of-4 mega-menu-item-1130' id='mega-menu-item-1130'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/category/announcements/scholarships/\">Υποτροφίες</a></li><li class='mega-menu-item mega-menu-item-type-taxonomy mega-menu-item-object-category mega-menu-columns-1-of-4 mega-menu-clear mega-menu-item-3961' id='mega-menu-item-3961'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/category/announcements/%ce%b5%ce%ba%ce%bb%ce%b5%ce%ba%cf%84%ce%bf%cf%81%ce%b9%ce%ba%ce%ac-%cf%83%cf%8e%ce%bc%ce%b1%cf%84%ce%b1/\">Εκλεκτορικά Σώματα</a></li><li class='mega-menu-item mega-menu-item-type-taxonomy mega-menu-item-object-category mega-menu-columns-1-of-4 mega-menu-item-3959' id='mega-menu-item-3959'><a class=\"mega-menu-link\" href=\"https://www.e-ce.uth.gr/category/announcements/%ce%b1%cf%80%ce%bf%cf%86%ce%ac%cf%83%ce%b5%ce%b9%cf%82-%cf%83%cf%85%ce%bb%ce%bb%ce%bf%ce%b3%ce%b9%ce%ba%cf%8e%ce%bd-%ce%bf%cf%81%ce%b3%ce%ac%ce%bd%cf%89%ce%bd/\">Αποφάσεις Συλλογικών Οργάνων</a></li><li class='mega-menu-item mega-menu-item-type-widget widget_recent_entries mega-menu-columns-4-of-4 mega-menu-clear mega-menu-item-recent-posts-3' id='mega-menu-item-recent-posts-3'>\t\t\t\t<h4 class=\"mega-block-title\">Πρόσφατες Ανακοινώσεις</h4>\t\t<ul>\n" +
                "\t\t                        <li>\n" +
                "                                                                <span class=\"post-date\">22/11/2016</span>\n" +
                "                                                        <a href=\"https://www.e-ce.uth.gr/%ce%b1%ce%bb%ce%bb%ce%b1%ce%b3%ce%ae-%ce%b7%ce%bc%ce%ad%cf%81%ce%b1%cf%82-%ce%b4%ce%b9%ce%b4%ce%b1%cf%83%ce%ba%ce%b1%ce%bb%ce%af%ce%b1%cf%82-%ce%bc%ce%b1%ce%b8%ce%ae%ce%bc%ce%b1%cf%84%ce%bf%cf%82-2/\">Αλλαγή Ημέρας Διδασκαλίας Μαθήματος Προγραμματισμός Ι</a>\n" +
                "                \n" +
                "                        </li>\n" +
                "\t\t                        <li>\n" +
                "                                                                <span class=\"post-date\">21/11/2016</span>\n" +
                "                                                        <a href=\"https://www.e-ce.uth.gr/%cf%80%cf%81%ce%bf%ce%ba%ce%ae%cf%81%cf%85%ce%be%ce%b7-%cf%80%ce%bc%cf%83-%cf%83%cf%84%ce%b7%ce%bd-%ce%b5%cf%80%ce%b9%cf%83%cf%84%ce%ae%ce%bc%ce%b7-%cf%84%ce%b5%cf%87%ce%bd%ce%bf%ce%bb%ce%bf/\">Προκήρυξη ΠΜΣ στην «Επιστήμη &amp; Τεχνολογία ΗΜΜΥ» για το Εαρινό Εξάμηνο 2016-2017</a>\n" +
                "                \n" +
                "                        </li>\n" +
                "\t\t                        <li>\n" +
                "                                                                <span class=\"post-date\">21/11/2016</span>\n" +
                "                                                        <a href=\"https://www.e-ce.uth.gr/jobposition-pgs-applied-informatics-spring-2016-2017/\">Πρόσκληση Εκδήλωσης Ενδιαφέροντος για το Π.Μ.Σ. «Εφαρμοσμένη Πληροφορική» του ΤΗΜΜΥ</a>\n" +
                "                \n" +
                "                        </li>\n" +
                "\t\t                        <li>\n" +
                "                                                                <span class=\"post-date\">21/11/2016</span>\n" +
                "                                                        <a href=\"https://www.e-ce.uth.gr/%ce%b2%cf%81%ce%ad%ce%b8%ce%b7%ce%ba%ce%b5-%ce%bc%cf%80%ce%bf%cf%85%cf%86%ce%ac%ce%bd-%cf%83%cf%84%ce%bf-%ce%ba%cf%84%ce%af%cf%81%ce%b9%ce%bf-%ce%b4%ce%b5%ce%bb%ce%b7%ce%b3%ce%b9%cf%8e%cf%81%ce%b3/\">Βρέθηκε Μπουφάν στο κτίριο Δεληγιώργη</a>\n" +
                "                \n" +
                "                        </li>\n" +
                "\t\t                        <li>\n" +
                "                                                                <span class=\"post-date\">11/11/2016</span>\n" +
                "                                                        <a href=\"https://www.e-ce.uth.gr/%ce%b8%ce%ad%cf%83%ce%b5%ce%b9%cf%82-%ce%b5%cf%81%ce%b5%cf%85%ce%bd%ce%b7%cf%84%cf%8e%ce%bd-%cf%83%cf%84%ce%bf-%ce%b5%cf%81%ce%b5%cf%85%ce%bd%ce%b7%cf%84%ce%b9%ce%ba%cf%8c-%cf%80%cf%81%cf%8c%ce%b3-2/\">Θέσεις Ερευνητών στο Ερευνητικό Πρόγραμμα MULTIDRONE</a>\n" +
                "                \n" +
                "                        </li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t\t\t</li></ul>\n" +
                "</li><li class='mega-menu-item mega-menu-item-type-post_type mega-menu-item-object-page mega-menu-item-has-children mega-align-bottom-left mega-menu-megamenu mega-menu-item-15032' id='mega-menu-item-15032'><a class=\"fa-phone mega-menu-link\" href=\"https://www.e-ce.uth.gr/contact-info/\" aria-haspopup=\"true\" tabindex=\"0\">Επικοινωνία</a>\n" +
                "<ul class=\"mega-sub-menu\">\n" +
                "<li class='mega-menu-item mega-menu-item-type-widget widget_text mega-menu-columns-5-of-8 mega-menu-item-text-2' id='mega-menu-item-text-2'>\t\t\t<div class=\"textwidget\"><a title=\"Επικοινωνία\" href=\"?page_id=76\"><img src=\"https://www.e-ce.uth.gr/wp-content/themes/office/images/staticmap.png\" width=\"699\" height=\"328\"></a></div>\n" +
                "\t\t</li><li class='mega-menu-item mega-menu-item-type-widget widget_text mega-menu-columns-3-of-8 mega-menu-item-text-3' id='mega-menu-item-text-3'>\t\t\t<div class=\"textwidget\"><table class=\"contact\" style=\"margin-top: 2px;\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<th class=\"center\" colspan=\"2\"><strong>Τμήμα Ηλεκτρολόγων Μηχανικών και Μηχανικών Υπολογιστών</strong></th>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td class=\"first\" colspan=\"2\">\n" +
                "<div class=\"cs-contact-info\">\n" +
                "<ul>\n" +
                "<li><i class=\"fa fa-map-marker\"></i><span>Γκλαβάνη 37 και 28ης Οκτωβρίου<br> Κτήριο Δεληγιώργη, 4ος Όροφος<br> ΤΚ 382 21, Βόλος</span> \n" +
                "</li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<th><strong>Τηλ.</strong></th>\n" +
                "<td>+30 24210 74967</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<th><strong>e-mail</strong></th>\n" +
                "<td><a href=\"mailto:gece@e-ce.uth.gr\">gece ΑΤ e-ce.uth.gr</a></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<th><strong>Τηλ. Π.Μ.Σ.</strong></th>\n" +
                "<td>+30 24210 74934</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<th><strong>e-mail Π.Μ.Σ.</strong></th>\n" +
                "<td><a href=\"mailto:pgsec@e-ce.uth.gr\">pgsec ΑΤ e-ce.uth.gr</a></td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<th><strong>Fax</strong></th>\n" +
                "<td>+30 24210 74997</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table></div>\n" +
                "\t\t</li></ul>\n" +
                "</li><li class=\"mega-menu-item\"><a href=\"https://www.e-ce.uth.gr/wp-login.php?action=logout&amp;_wpnonce=ac37173dc4\" class=\"fa-sign-out mega-menu-link\">Έξοδος</a></li></ul></div>\t<!--</nav> /navigation -->  \n" +
                "\t\t\n" +
                "\t   \n" +
                "\t\t\t\n" +
                "\t\t\t\t\t\n" +
                "\t<div class=\"container clearfix fitvids  sidebar-right\">\n" +
                "\t\n" +
                "\n" +
                "\t\n" +
                "\t\t\n" +
                "\t<div id=\"slider-wrap\">\n" +
                "\t\t<div id=\"full-slides\" class=\"flexslider clearfix\">\n" +
                "\t\t\t<ul class=\"slides\">\n" +
                "\t\t\t\t\t\t\t\t\t<li class=\"slide\">\n" +
                "\t\t\t\t\t\t<img src=\"https://www.e-ce.uth.gr/wp-content/uploads/2012/05/header_sp1.jpg\" alt=\"header_sp\" />\n" +
                "\t\t\t\t\t\t\t\t\t\t\t</li><!--/slide --> \n" +
                "\t\t\t\t\t\t\t</ul><!-- /slides -->\n" +
                "\t\t</div><!--/full-slides -->\n" +
                "\t</div><!-- /slider-wrap -->\n" +
                "\t\n" +
                "\n" +
                "\t<div id=\"page-heading\">\n" +
                "\t<!-- nfragog adds edit link in pages -->\n" +
                "\t \n" +
                "<!-- end nfragog -->\n" +
                "\t\t<h1>Εβδομαδιαίο Ωρολόγιο Π.Π.Σ. Χειμερινού Εξαμήνου</h1>\n" +
                "\t\t<nav id=\"breadcrumbs\"><span class=\"breadcrumbs-title\"></span><a href=\"https://www.e-ce.uth.gr\">Αρχική</a> &raquo; Σπουδές &raquo; <a href=\"https://www.e-ce.uth.gr/studies/undergraduate/\">Προπτυχιακές Σπουδές</a> &raquo; <a href=\"https://www.e-ce.uth.gr/studies/undergraduate/fall-timetable/\">Ωρολόγιο Π.Π.Σ. Χειμερινού</a> &raquo; <span>Εβδομαδιαίο Ωρολόγιο Π.Π.Σ. Χειμερινού Εξαμήνου</span></nav>\t\n" +
                "\t</div><!-- /page-heading -->\n" +
                "\n" +
                "\n" +
                "<article class=\"post clearfix\">\n" +
                "\n" +
                "\t<div class=\"entry clearfix\">\n" +
                "\t\t\t<div class=\"tabs tab-shortcode office-tabs\" style=\"margin: 0px 0; display: none;\"><ul class=\"clearfix \"><li><a href=\"#tabs-1-1\">Δευτέρα</a></li><li><a href=\"#tabs-1-2\">Τρίτη</a></li><li><a href=\"#tabs-1-3\">Τετάρτη</a></li><li><a href=\"#tabs-1-4\">Πέμπτη</a></li><li><a href=\"#tabs-1-5\">Παρασκευή</a></li></ul>\n" +
                "<div id=\"tabs-1-1\" class=\"tab_content clearfix\"><div style=\"overflow-x:auto;\">\n" +
                "<table class=\"course_schedule\">\n" +
                "<tr>\n" +
                "<th width=\"95\">Έναρξη &#8211; Λήξη</th>\n" +
                "<th>Μάθημα</th>\n" +
                "<th>Τύπος</th>\n" +
                "<th>Αίθουσα</th>\n" +
                "<th>Διδάσκων</th>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>08:00 &#8211; 10:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1437\">Γραμμική Άλγεβρα</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αμφ. Κορδάτου</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1358\">Βάβαλης Εμμανουήλ</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>08:00 &#8211; 10:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1661\">Σχεδιασμός Διαδικτυακών Πρωτοκόλλων</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Εργ. Α3</td>\n" +
                "<td>Συμβασιούχος Διδάσκων</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>08:00 &#8211; 10:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1661\">Σχεδιασμός Διαδικτυακών Πρωτοκόλλων</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Εργ. Α1</td>\n" +
                "<td>Συμβασιούχος Διδάσκων</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>09:00 &#8211; 11:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1610\">Τεχνολογίες Εκπαίδευσης</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Σ</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=16994\">Τσαλαπάτα Χαρίκλεια</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>10:00 &#8211; 12:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=16151\">Προγραμματισμός Ι</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αμφ. Κορδάτου</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1369\">Αντωνόπουλος Χρήστος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>10:00 &#8211; 12:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1594\">Οργάνωση και Σχεδίαση Η/Υ</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αμφ. Σαράτση</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1364\">Μπέλλας Νικόλαος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>11:00 &#8211; 13:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1613\">Τεχνητή Νοημοσύνη</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Σ</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1372\">Δασκαλοπούλου Ασπασία</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>12:00 &#8211; 14:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=16147\">Λογισμός Ι</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αμφ. Κορδάτου</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1261\">Τσομπανοπούλου Παναγιώτα</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>12:00 &#8211; 14:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1445\">Δομές Δεδομένων</a></td>\n" +
                "<td>Φροντιστήριο</td>\n" +
                "<td>Αμφ. Σαράτση</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=12677\">Θάνος Γιώργιος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>13:00 &#8211; 17:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=15083\">Τεχνική και Ακαδημαϊκή Γραφή</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Σ</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=5577\">Ντέλιου Ελευθερία</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>14:00 &#8211; 17:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1596\">Ηλεκτρικές Μετρήσεις</a></td>\n" +
                "<td>Εργαστήριο</td>\n" +
                "<td>Εργ. Α3</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=18593\">Λαζαρόπουλος Γεώργιος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>14:00 &#8211; 16:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1444\">Ψηφιακά Συστήματα VLSI</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Γ1</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1348\">Σταμούλης Γεώργιος</a><br />\n" +
                "<a href=\"https://www.e-ce.uth.gr/?p=8183\">Σωτηρίου Χρήστος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>16:00 &#8211; 19:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1442\">Ανάλυση Κυκλωμάτων</a></td>\n" +
                "<td>Εργαστήριο</td>\n" +
                "<td>Εργ. Β2</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1387\">Πλέσσας Φώτιος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>16:00 &#8211; 18:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1646\">Τεχνολογίες Παγκόσμιου Ιστού</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Γ1</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1358\">Βάβαλης Εμμανουήλ</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>16:00 &#8211; 18:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1749\">Αλγόριθμοι CAD</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Δ1</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=8183\">Σωτηρίου Χρήστος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>17:00 &#8211; 19:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1781\">Κινητός και Διάχυτος Υπολογισμός</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Σ</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1381\">Κατσαρός Δημήτριος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>18:00 &#8211; 21:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1633\">Εισαγωγή στην Επιχειρηματικότητα</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αμφ. Οικονομικού</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=5662\">Σταμπουλής Γεώργιος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>18:00 &#8211; 20:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=2920\">Ηλεκτρικές Εγκαταστάσεις</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Εργ. Α3</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1355\">Χούστης Ηλίας</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>19:00 &#8211; 21:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=380\">Ψηφιακή Σχεδίαση</a></td>\n" +
                "<td>Εργαστήριο</td>\n" +
                "<td>Αμφ. Κορδάτου</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1348\">Σταμούλης Γεώργιος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>19:00 &#8211; 21:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1616\">Ανάλυση Κυκλωμάτων ΙΙ</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Σ</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=18946\">Μάργαρης Ιωάννης</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>19:00 &#8211; 21:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1817\">Εργαστήριο Αναλογικών Κυκλωμάτων</a></td>\n" +
                "<td>Εργαστήριο</td>\n" +
                "<td>Εργ. Β2</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1387\">Πλέσσας Φώτιος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div id=\"tabs-1-2\" class=\"tab_content clearfix\"><div style=\"overflow-x:auto;\">\n" +
                "<table class=\"course_schedule\">\n" +
                "<tr>\n" +
                "<th width=\"95\">Έναρξη &#8211; Λήξη</th>\n" +
                "<th>Μάθημα</th>\n" +
                "<th>Τύπος</th>\n" +
                "<th>Αίθουσα</th>\n" +
                "<th>Διδάσκων</th>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>08:00 &#8211; 10:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1445\">Δομές Δεδομένων</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αμφ. Σαράτση</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1050\">Μποζάνης Παναγιώτης</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>08:00 &#8211; 10:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1643\">Σχεδίαση και Ανάπτυξη Λογισμικού</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Σ</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=16994\">Τσαλαπάτα Χαρίκλεια</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>08:00 &#8211; 10:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1817\">Εργαστήριο Αναλογικών Κυκλωμάτων</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Εργ. Α3</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1387\">Πλέσσας Φώτιος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>09:00 &#8211; 11:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1443\">Ταυτόχρονος Προγραμματισμός</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Γ1</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=733\">Λάλης Σπύρος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>10:00 &#8211; 12:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1442\">Ανάλυση Κυκλωμάτων</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αμφ. Σαράτση</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1387\">Πλέσσας Φώτιος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>10:00 &#8211; 12:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1645\">Λογικός και Συναρτησιακός Υπολογισμός</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Σ</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1372\">Δασκαλοπούλου Ασπασία</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>11:00 &#8211; 13:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1778\">Περιβάλλοντα Επίλυσης Προβλημάτων</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Γ1</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1355\">Χούστης Ηλίας</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>12:00 &#8211; 15:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=2676\">Αγγλική Γλώσσα και Τεχνική Ορολογία</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Εργ. Α1</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=5577\">Ντέλιου Ελευθερία</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>12:00 &#8211; 14:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1613\">Τεχνητή Νοημοσύνη</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Σ</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1372\">Δασκαλοπούλου Ασπασία</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>12:00 &#8211; 14:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1654\">Ηλεκτρομαγνητικά Πεδία ΙΙ</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Δ1</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1379\">Ευμορφόπουλος Νέστωρ</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>14:00 &#8211; 16:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1617\">Ψηφιακή Επεξεργασία Σημάτων</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Σ</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1367\">Ποταμιάνος Γεράσιμος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>14:00 &#8211; 16:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1649\">Εργαστήριο Ψηφιακών Συστημάτων</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Εργ. Α3</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=8183\">Σωτηρίου Χρήστος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>15:00 &#8211; 17:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1441\">Πιθανότητες</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αμφ. Σαράτση</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1378\">Αργυρίου Αντώνιος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>16:00 &#8211; 19:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1780\">Προγραμματισμός Ασύρματων Δικτύων Αισθητήρων</a></td>\n" +
                "<td>Εργαστήριο</td>\n" +
                "<td>Εργ. Β2</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=733\">Λάλης Σπύρος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>16:00 &#8211; 19:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1823\">Εναλλακτικές Μορφές Ενέργειας</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Γ1</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1351\">Τσουκαλάς Ελευθέριος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>16:00 &#8211; 18:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1749\">Αλγόριθμοι CAD</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Δ1</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=8183\">Σωτηρίου Χρήστος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>18:00 &#8211; 20:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1553\">Φυσική Ι</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αμφ. Κορδάτου</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=10350\">Βαβουγυιός Διονύσιος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>18:00 &#8211; 22:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1605\">Δίκτυα Υπολογιστών Ι</a></td>\n" +
                "<td>Εργαστήριο</td>\n" +
                "<td>Εργ. Α3</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=4856\">Κοράκης Αθανάσιος</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr class=\"sbody\">\n" +
                "<td>19:00 &#8211; 21:00</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1786\">Αλγόριθμοι Προσομοίωσης Κυκλωμάτων</a></td>\n" +
                "<td>Διάλεξη</td>\n" +
                "<td>Αιθ. Δ2</td>\n" +
                "<td><a href=\"https://www.e-ce.uth.gr/?p=1379\">Ευμορφόπουλος Νέστωρ</a>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "</div>\n" +
                "</div>\n" +

                "</div>\n" +
                "</div>\n" +
                "<p style=\"margin-top: 20px; margin-bottom: 0px;\">\n" +
                "* Το μάθημα <a href=\"https://www.e-ce.uth.gr/?page_id=1742\">ΗΥ421 Συστήματα Υπολογισμού Υψηλών Επιδόσεων</a> θα διδάσκεται στο Εργαστήριο Α1 τις παρακάτω ημέρες:</p>\n" +
                "<ul>\n" +
                "<li>Δευτέρα 14:00 &#8211; 16:00</li>\n" +
                "<li>Τρίτη 18:00 &#8211; 20:00</li>\n" +
                "</ul>\n" +
                "\t</div><!-- /entry -->\n" +
                "\n" +
                "\t\t\n" +
                "</article><!-- /post -->\n" +
                "\n" +
                "<div id=\"sidebar\">\n" +
                "\t<div class=\"sidebar-box widget_search clearfix\"><form method=\"get\" id=\"searchbar\" action=\"https://www.e-ce.uth.gr/\">\n" +
                "<input type=\"text\" size=\"16\" name=\"s\" value=\"Αναζήτηση\" onfocus=\"if(this.value==this.defaultValue)this.value='';\" onblur=\"if(this.value=='')this.value=this.defaultValue;\" id=\"search\" />\n" +
                "<input type=\"submit\" id=\"searchsubmit\" value=\"\" />\n" +
                "<input type='hidden' name='lang' value='el' /></form></div>\t\t<div class=\"sidebar-box widget_recent_entries clearfix\">\t\t<h4><span>Πρόσφατες Ανακοινώσεις</span></h4>\t\t<ul>\n" +
                "\t\t                        <li>\n" +
                "                                                                <span class=\"post-date\">22/11/2016</span>\n" +
                "                                                        <a href=\"https://www.e-ce.uth.gr/%ce%b1%ce%bb%ce%bb%ce%b1%ce%b3%ce%ae-%ce%b7%ce%bc%ce%ad%cf%81%ce%b1%cf%82-%ce%b4%ce%b9%ce%b4%ce%b1%cf%83%ce%ba%ce%b1%ce%bb%ce%af%ce%b1%cf%82-%ce%bc%ce%b1%ce%b8%ce%ae%ce%bc%ce%b1%cf%84%ce%bf%cf%82-2/\">Αλλαγή Ημέρας Διδασκαλίας Μαθήματος Προγραμματισμός Ι</a>\n" +
                "                \n" +
                "                        </li>\n" +
                "\t\t                        <li>\n" +
                "                                                                <span class=\"post-date\">21/11/2016</span>\n" +
                "                                                        <a href=\"https://www.e-ce.uth.gr/%cf%80%cf%81%ce%bf%ce%ba%ce%ae%cf%81%cf%85%ce%be%ce%b7-%cf%80%ce%bc%cf%83-%cf%83%cf%84%ce%b7%ce%bd-%ce%b5%cf%80%ce%b9%cf%83%cf%84%ce%ae%ce%bc%ce%b7-%cf%84%ce%b5%cf%87%ce%bd%ce%bf%ce%bb%ce%bf/\">Προκήρυξη ΠΜΣ στην «Επιστήμη &amp; Τεχνολογία ΗΜΜΥ» για το Εαρινό Εξάμηνο 2016-2017</a>\n" +
                "                \n" +
                "                        </li>\n" +
                "\t\t                        <li>\n" +
                "                                                                <span class=\"post-date\">21/11/2016</span>\n" +
                "                                                        <a href=\"https://www.e-ce.uth.gr/jobposition-pgs-applied-informatics-spring-2016-2017/\">Πρόσκληση Εκδήλωσης Ενδιαφέροντος για το Π.Μ.Σ. «Εφαρμοσμένη Πληροφορική» του ΤΗΜΜΥ</a>\n" +
                "                \n" +
                "                        </li>\n" +
                "\t\t                        <li>\n" +
                "                                                                <span class=\"post-date\">21/11/2016</span>\n" +
                "                                                        <a href=\"https://www.e-ce.uth.gr/%ce%b2%cf%81%ce%ad%ce%b8%ce%b7%ce%ba%ce%b5-%ce%bc%cf%80%ce%bf%cf%85%cf%86%ce%ac%ce%bd-%cf%83%cf%84%ce%bf-%ce%ba%cf%84%ce%af%cf%81%ce%b9%ce%bf-%ce%b4%ce%b5%ce%bb%ce%b7%ce%b3%ce%b9%cf%8e%cf%81%ce%b3/\">Βρέθηκε Μπουφάν στο κτίριο Δεληγιώργη</a>\n" +
                "                \n" +
                "                        </li>\n" +
                "\t\t\t\t</ul>\n" +
                "\t\t</div>\t\t<div class=\"sidebar-box widget_text_icl clearfix\">        <div class=\"textwidget\"><div class=\"roundbox\" style=\"text-align: center;\">\n" +
                "<h4><span>e-Yπηρεσίες</span></h4>\n" +
                "<ul class=\"services\">\n" +
                "<li class=\"webmail\"><a href=\"https://webmail.uth.gr/login.php\" target=\"_blank\"></a></li>\n" +
                "<li class=\"eclass\"><a href=\"http://eclass.uth.gr\" target=\"_blank\"></a></li>\n" +
                "<li class=\"unistudent\"><a href=\"https://euniversity.uth.gr/unistudent/\" target=\"_blank\"></a></li>\n" +
                "<li class=\"eudoxus\"><a href=\"http://eudoxus.gr/\" target=\"_blank\"></a></li>\n" +
                "<li class=\"academicid\"><a href=\"https://submit-academicid.minedu.gov.gr/\" target=\"_blank\"></a></li>\n" +
                "</ul>\n" +
                "</div></div>\n" +
                "</div><div class=\"sidebar-box widget_text_icl clearfix\">        <div class=\"textwidget\"></div>\n" +
                "</div></div><!-- /sidebar -->\n" +
                "<div class=\"clear\"></div>\n" +
                "</div><!-- /container -->\n" +
                "\n" +
                "    <footer id=\"footer\">\n" +
                "    \n" +
                "    \t        \n" +
                "        <div id=\"footer-widget-wrap\" class=\"clearfix \">\n" +
                "    \n" +
                "            <div id=\"footer-left\" class=\"clearfix\">\n" +
                "            \t<div class=\"footer-widget widget_text_icl clearfix\"><h4>Επικοινωνία</h4>        <div class=\"textwidget\">\t\t\t\t\t\t\t\t<ul class=\"list-info\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<li><i class=\"fa fa-map-marker fa-lg\"></i> <span>Γκλαβάνη 37 και 28ης Οκτωβρίου, Βόλος</span></li>\n" +
                "\t\t\t\t\t\t\t\t\t\t<li><i class=\"fa fa-phone fa-lg\"></i><span> Τηλέφωνο: <a href=\"callto:00302421074967\">+30 24210 74967</a></span></li>\n" +
                "\t\t\t\t\t\t\t\t\t\t<li><i class=\"fa fa-fax fa-lg\"></i><span> Fax: <a href=\"callto:00302421074997\">+30 24210 74997</a></span></li>\n" +
                "\t\t\t\t\t\t\t\t\t\t<li><i class=\"fa fa-envelope fa-lg\"></i><span> Email: <a href=\"mailto:gece@e-ce.uth.gr\">gece@e-ce.uth.gr</a></span></li>\n" +
                "\t\t\t\t\t\t\t\t\t</ul>\n" +
                "</div>\n" +
                "</div>            </div><!-- /footer-left -->\n" +
                "            \n" +
                "            <div id=\"footer-middle\" class=\"clearfix\">\n" +
                "            \t<div class=\"footer-widget widget_nav_menu clearfix\"><h4>Ανακοινώσεις</h4><div class=\"menu-footer-menu-%ce%b5%ce%bb%ce%bb%ce%b7%ce%bd%ce%b9%ce%ba%ce%ac-container\"><ul id=\"menu-footer-menu-%ce%b5%ce%bb%ce%bb%ce%b7%ce%bd%ce%b9%ce%ba%ce%ac\" class=\"menu\"><li id=\"menu-item-17212\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-17212\"><a href=\"https://www.e-ce.uth.gr/category/announcements/general-announcements/\">Γενικές Ανακοινώσεις</a></li>\n" +
                "<li id=\"menu-item-17213\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-17213\"><a href=\"https://www.e-ce.uth.gr/category/announcements/academic-news/\">Ακαδημαϊκά Νέα</a></li>\n" +
                "<li id=\"menu-item-17214\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-17214\"><a href=\"https://www.e-ce.uth.gr/category/announcements/positions/\">Θέσεις Εργασίας</a></li>\n" +
                "<li id=\"menu-item-17218\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-17218\"><a href=\"https://www.e-ce.uth.gr/category/announcements/scholarships/\">Υποτροφίες</a></li>\n" +
                "<li id=\"menu-item-17219\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-17219\"><a href=\"https://www.e-ce.uth.gr/category/announcements/%ce%b1%cf%80%ce%bf%cf%86%ce%ac%cf%83%ce%b5%ce%b9%cf%82-%cf%83%cf%85%ce%bb%ce%bb%ce%bf%ce%b3%ce%b9%ce%ba%cf%8e%ce%bd-%ce%bf%cf%81%ce%b3%ce%ac%ce%bd%cf%89%ce%bd/\">Αποφάσεις Συλλογικών Οργάνων</a></li>\n" +
                "</ul></div></div>            </div><!-- /footer-middle -->\n" +
                "            \n" +
                "            <div id=\"footer-right\" class=\"clearfix\">\n" +
                "            \t<div class=\"footer-widget widget_text_icl clearfix\"><h4>Θα μας Βρείτε</h4>        <div class=\"textwidget\"><div class=\"menu-footer-content widget_nav_menu_findus\">\n" +
                "<ul class=\"footer-menu\">\n" +
                "<li>\n" +
                "<a href=\"https://www.facebook.com/ece.uth.gr\" target=\"_blank\"><i class=\"fa fa-facebook-square fa-lg\"></i> Facebook</a>\n" +
                "</li>\n" +
                "<li>\n" +
                "<a href=\"https://twitter.com/infuth\" target=\"_blank\"><i class=\"fa fa-twitter-square fa-lg\"></i> Twitter</a></li>\n" +
                "<li class=\"page_item\"><a href=\"https://www.youtube.com/user/ECEUTH\" target=\"_blank\"><i class=\"fa fa-youtube-square fa-lg\"></i> Youtube</a>\n" +
                "</li>\n" +
                "<li>\n" +
                "<a href=\"https://www.linkedin.com/groups/3147579\" target=\"_blank\"><i class=\"fa fa-linkedin-square fa-lg\"></i> Linkedin</a>\n" +
                "</li>\n" +
                "</ul>\n" +
                "</div></div>\n" +
                "</div>            </div><!-- /footer-right -->\n" +
                "        \n" +
                "        </div><!-- /footer-widget-wrap -->\n" +
                "        \n" +
                "                \n" +
                "        <a href=\"#toplink\" id=\"toplink\"><i class=\"fa fa-chevron-up fa-2x\" aria-hidden=\"true\"></i></a>\n" +
                "    \n" +
                "    </footer><!-- /footer -->\n" +
                "    \n" +
                "</div><!-- /wrap -->\n" +
                "\n" +
                "</body>\n" +
                "</html>\n" );

        // ul class="clearfix "><li><a href="#tabs-1-1">Δευτέρα</a></li><li><a href="#tabs-1-2">Τρίτη</a></li><li><a href="#tabs-1-3">Τετάρτη</a></li><li><a href="#tabs-1-4">Πέμπτη</a></li><li><a href="#tabs-1-5">Παρασκευή</a></li></ul>

        Document doc = Jsoup.parse( html.toString() );

        ArrayList<Course> courses = new ArrayList<Course>();

        Elements dayElement = doc.getElementsByAttributeValue( "href", "#tabs-1-1" );
        String day = dayElement.html();

        Element dayElementInfo = doc.getElementById( "tabs-1-1" );

        Elements courseElementInfo = dayElementInfo.getElementsByClass( "sbody" );

        for ( Element entry : courseElementInfo )
        {
            Elements info = entry.getElementsByTag( "td" );

            String[] times = info.get( 0 ).text().split( " " );

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "HH:mm" );
            Date startTime = null;
            Date endTime = null;

            try
            {
                startTime = simpleDateFormat.parse( times[0] );
                endTime = simpleDateFormat.parse( times[2] );
            }
            catch ( ParseException e )
            {
                e.printStackTrace();
            }

            String courseName = info.get( 1 ).text();
            String courseType = info.get( 2 ).text();
            String classroom = info.get( 3 ).text();
            String instructor = info.get( 4 ).text();

            CourseInfo courseInfo = new CourseInfo( day, classroom, startTime, endTime );
            Course course = new Course( courseName, instructor );
            boolean found = false;


            if ( courses.isEmpty() )
            {
                course.addCourseInfo( courseInfo );
                courses.add( course );
            }

            for ( int i = 0; i < courses.size(); i++ )
            {
                if ( courses.get( i ).getCourseName().equals( courseName ) )
                {
                    courses.get( i ).addCourseInfo( courseInfo );
                    found = true;
                    break;
                }
            }

            if ( !found )
            {
                course.addCourseInfo( courseInfo );
                courses.add( course );
            }
        }



        for ( Course course : courses )
        {
            System.out.println( course.getCourseName() );
        }


        return null;
    }
    public String parseUniversity( String userName, String password )
    {

        String loginUrl = "https://10.64.4.64:443/unistudent/studentMain.asp";
        String gradesUrl= "https://10.64.4.64/unistudent/stud_CResults.asp";
        String page;

//        Parser http = new Parser();

        CookieHandler.setDefault( new CookieManager() ); // make sure cookies are turned on

        try
        {
             page = GetPageContent( loginUrl ); // 1. Send a "GET" request, so that you can extract the form's data.
//            String postParams = getFormParams( page, userName, password );
//
//            sendPost( loginUrl, postParams ); // 2. Construct above post's content and then send a POST request for authentication
//
//            //String result = http.GetPageContent( url ); // 3. success then go to the student details page.
//            String result = GetPageContent( gradesUrl );
//            grades =  getStudentGrades( result );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            return e.toString();
        }

        return page;
    }

    public static String getStudentGrades( String html ) throws IOException
    {

        Document doc = Jsoup.parse( html );

        Elements rows = doc.getElementsByClass("topBorderLight");
        Elements rows2= doc.getElementsByClass("redFonts");

        ArrayList<String> gradesArray = new ArrayList<String>();
        ArrayList<String> lessonsArray = new ArrayList<String>();

        for(Element row1: rows2) {
            String grade = row1.getElementsByTag("span").first().ownText();

            if (!grade.equals("")) {

                gradesArray.add(grade);
            }
        }


        for(Element row : rows) {

            String lesson = row.getElementsByTag("td").first().text();
            if(row.hasAttr("colspan")) {

                lessonsArray.add(lesson);
            }
        }
        for(int i = 0; i < lessonsArray.size(); i++) {
            System.out.print( "Lesson: " + lessonsArray.get( i ) + "\n" );
            System.out.println("Grade: "+ gradesArray.get(i));
        }
        return "ok";
    }

    private void sendPost( String loginUrl, String postParams ) throws Exception
    {

        URL obj = new URL( loginUrl );
        conn = ( HttpsURLConnection ) obj.openConnection();

        // Acts like a browser
        conn.setInstanceFollowRedirects(true);
        conn.setUseCaches( false );
        conn.setRequestMethod( "POST" );
        conn.setRequestProperty( "Host", "euniversity.uth.gr" );
        conn.setRequestProperty( "User-Agent", USER_AGENT );
        conn.setRequestProperty( "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8" );
        conn.setRequestProperty( "Accept-Language", "el-GR,el;q=0.8" );
        conn.setRequestProperty( "Accept-Encoding", "gzip, deflate, br" );
        for ( String cookie : this.cookies )
        {
            conn.addRequestProperty( "Cookie", cookie.split( ";", 1 )[0] );
        }
        conn.setRequestProperty( "Connection", "keep-alive" );
        conn.setRequestProperty( "Referer", "https://euniversity.uth.gr/unistudent/login.asp" );
        conn.setRequestProperty( "Content-Type", " application/x-www-form-urlencoded; charset=utf-8" );
        conn.setRequestProperty( "Content-Length", Integer.toString( postParams.length() ) );

        conn.setDoOutput( true );
        conn.setDoInput( true );

        // Send post request
        DataOutputStream wr = new DataOutputStream( conn.getOutputStream() );
        wr.writeBytes( postParams );
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
        System.out.println( "\nSending 'POST' request to URL : " + loginUrl );
        System.out.println( "Post parameters : " + postParams );
        System.out.println( "Response Code : " + responseCode );

        BufferedReader in = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ( ( inputLine = in.readLine() ) != null )
        {
            response.append( inputLine + "\n" );
        }

        in.close();

        byte[] defaultBytes = response.toString().getBytes();

        String html = new String(defaultBytes, "windows-1253");


        getStudentDetails(  html );
    }

    private String GetPageContent( String url ) throws Exception
    {

        URL obj = new URL( url );
        conn = ( HttpsURLConnection ) obj.openConnection();
        conn.connect();
        System.out.println( "Conn ok" );
        // default is GET
        conn.setRequestMethod( "GET" );
        conn.setUseCaches( false );

        // act like a browser
        conn.setRequestProperty( "User-Agent", USER_AGENT );
        conn.setRequestProperty( "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8; charset=utf-8" );
        conn.setRequestProperty( "Accept-Language", "el-GR,el;q=0.8" );
        if ( cookies != null )
        {
            for ( String cookie : this.cookies )
            {
                conn.addRequestProperty( "Cookie", cookie.split( ";", 1 )[0] );
            }
        }
        int responseCode =0;// = conn.getResponseCode();
        System.out.println( "\nSending 'GET' request to URL : " + url );
        System.out.println( "Response Code : " + responseCode + " message: " + conn.getResponseMessage() );

        BufferedReader in = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ( ( inputLine = in.readLine() ) != null )
        {
            response.append( inputLine + "\n" );
        }
        in.close();

        // Get the response cookies
        setCookies( conn.getHeaderFields().get( "Set-Cookie" ) );

        return response.toString();

    }

    public String getStudentDetails( String html )
    {
        Document doc = Jsoup.parse( html );
        Student student = new Student();

        Elements elements = doc.select( "tr [height=20]" );

        Element studentIDTag = doc.getElementById( "subheader" );
        student.setFirstName( elements.first().html() );
        System.out.println( elements.first() );
        Elements studentIDinnerTag = studentIDTag.getElementsByTag( "span" );
        String str = studentIDinnerTag.first().html();
        String lastName =  elements.first().child( 1 ).html();
        student.setStudentID( Integer.parseInt( str.substring( str.indexOf( "(" ) + 1, str.indexOf( ")" ) ) ) );
        student.setLastName( lastName );
        System.out.println( "Your student id is: " + student.getStudentID() + "\nYour last name is: " + student.getLastName() );

        return student.toString();
    }

    public String getFormParams( String html, String username, String password ) throws UnsupportedEncodingException
    {

        System.out.println( "Extracting form's data..." );

        Document doc = Jsoup.parse( html );

        // Google form id
        Element loginform = doc.getElementById( "login" );
        Elements inputElements = loginform.getElementsByTag( "input" );
        List<String> paramList = new ArrayList<String>();
        for ( Element inputElement : inputElements )
        {
            String key = inputElement.attr( "name" );
            String value = inputElement.attr( "value" );

            if ( key.equals( "userName" ) )
                value = username;
            else if ( key.equals( "pwd" ) )
                value = password;
            paramList.add( key + "=" + URLEncoder.encode( value, "UTF-8" ) );
        }

        // build parameters list
        StringBuilder result = new StringBuilder();
        for ( String param : paramList )
        {
            if ( result.length() == 0 )
            {
                result.append( param );
            }
            else
            {
                result.append( "&" + param );
            }
        }

        result.append( "login" );
//        System.out.println( result );

        return result.toString();
    }

    public List<String> getCookies()
    {
        return cookies;
    }

    public void setCookies( List<String> cookies )
    {
        this.cookies = cookies;
    }

}