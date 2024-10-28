import grails.plugin.mail.MailService

class NewsLetterJob {

    MailService mailService

    static triggers = {
        cron name: 'myCronTrigger', cronExpression: "0 03 12 28 10 ? *"
    }

    def execute() {
        mailService.sendMail {
            to "immayl9798@gmail.com"
            subject "Test Email"
            body "This is a test email."
        }
    }
}
