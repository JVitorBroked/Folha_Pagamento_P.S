package Class;
import java.time.LocalDate;

public class PaymentSchedule{

    private LocalDate last_payment;
    private String schedule;
    private LocalDate nextDatePayment;

    public PaymentSchedule(String schedule){
        this.schedule = schedule;
        this.last_payment = null;
        
        //add metodo para selecioar proximo dia de pagamento(based at schedule)
        this.nextDatePayment = Processar_new_date_to_pay(schedule);
        
    }

    public String getSchedule() {
        return schedule;
    }
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public LocalDate getNextDatePayment() {
        return nextDatePayment;
    }
    
    public void setNextDatePayment(LocalDate nextDatePayment) {
        this.nextDatePayment = nextDatePayment;
    }

    public LocalDate getLast_payment() {
        return last_payment;
    }
    
    public void setLast_payment(LocalDate last_payment) {
        this.last_payment = last_payment;
    }

    public LocalDate criar_semanal(){

        LocalDate date_now = LocalDate.now();

        if(date_now.getDayOfWeek().name().equals("FRIDAY")){
            date_now = date_now.plusDays(1);
        }

        while(!(date_now.getDayOfWeek().name().equals("FRIDAY"))){
            date_now = date_now.plusDays(1);
        }
        LocalDate next_day_to_pay = date_now;
        return next_day_to_pay;
    }
    
    public LocalDate criar_bisemanal(){
        
        LocalDate date_now = LocalDate.now();
        
        if(date_now.getDayOfWeek().name().equals("FRIDAY")){
            date_now = date_now.plusDays(1);
        }
        
        int weeks = 0;
        while(!(date_now.getDayOfWeek().name().equals("FRIDAY")) || weeks < 1){
            if(date_now.getDayOfWeek().name().equals("FRIDAY")){
                weeks++;
            }
            date_now = date_now.plusDays(1);
        }
        LocalDate next_day_to_pay = date_now;
        return next_day_to_pay;
    }
    
    public LocalDate criar_mensal(){

        LocalDate next_day_to_pay = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().plus(1), 1).minusDays(1); 
         
        if(next_day_to_pay.getDayOfWeek().name().equals("SUNDAY")){
            next_day_to_pay = next_day_to_pay.minusDays(2);
        }else if(next_day_to_pay.getDayOfWeek().name().equals("SATURDAY")){
            next_day_to_pay = next_day_to_pay.minusDays(1);
        } 
        return next_day_to_pay;
    }
    
    public LocalDate criar_custom(String schedule){
        return null;
    }

    public LocalDate Processar_new_date_to_pay(String schedule){
        
        if(schedule.equals("semanal")){
            
            LocalDate date_now = LocalDate.now();
            if(date_now.getDayOfWeek().name().equals("FRIDAY")){
                date_now = date_now.plusDays(1);
            }
            while(!(date_now.getDayOfWeek().name().equals("FRIDAY"))){
                date_now = date_now.plusDays(1);
            }
            LocalDate next_day_to_pay = date_now;
            return next_day_to_pay;

        }else if(schedule.equals("bisemanal")){

            LocalDate date_now = LocalDate.now();
        
            if(date_now.getDayOfWeek().name().equals("FRIDAY")){
                date_now = date_now.plusDays(1);
            }
        
            int weeks = 0;
            while(!(date_now.getDayOfWeek().name().equals("FRIDAY")) || weeks < 1){
                if(date_now.getDayOfWeek().name().equals("FRIDAY")){
                    weeks++;
                }
                date_now = date_now.plusDays(1);
            }
            LocalDate next_day_to_pay = date_now;
            return next_day_to_pay;

        }else if(schedule.equals("mensal")){

            LocalDate next_day_to_pay = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth().plus(1), 1).minusDays(1); 
         
            if(next_day_to_pay.getDayOfWeek().name().equals("SUNDAY")){
                next_day_to_pay = next_day_to_pay.minusDays(2);
            }else if(next_day_to_pay.getDayOfWeek().name().equals("SATURDAY")){
                next_day_to_pay = next_day_to_pay.minusDays(1);
            } 
            return next_day_to_pay;

        }else{

            //adicionar metodo shedule pagamento customizado
            return null; 
        }    
    }
}