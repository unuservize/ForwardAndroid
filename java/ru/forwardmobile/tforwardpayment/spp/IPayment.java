package ru.forwardmobile.tforwardpayment.spp;

import java.util.Collection;
import java.util.Date;

/**
 * @author Vasiliy Vanin
 */
public interface IPayment {
    
    public static final int NEW       = 0;
    public static final int CHECKED   = 1;
    public static final int COMMITED  = 2;
    public static final int DONE      = 3;
    public static final int FAILED    = 4;
    public static final int CANCELLED = 5;

    /** @return Integer Идентификатор записи */
    public Integer                  getId();
    public void                     setId(Integer id);

    /** @return Integer Идентификатор транзакции */
    public Integer                  getTransactionId();
    public void                     setTransactionId(Integer transactionId);

    /** @return Collection<IField>  Набор полей */
    public Collection<IField>       getFields();

    /** @return Integer Идентификатор платежной системы */
    public Integer                  getPsid();

    /** @return Double  Сумма платежа к зачислению в рублях с копейками */
    public Double                   getValue();
    public void                     setValue(Double value);

    /** @return Double  Сумма платежа полная в рублях с копейками */
    public Double                   getFullValue();

    /** @return Integer Код ошибки */
    public Integer                  getErrorCode();
    public void                     setErrorCode(Integer errorCode);

    public String                   getErrorDescription();
    public void                     setErrorDescription(String errorDescription);

    /** @return Date    Начало платежа */
    public Date                     getStartDate();
    public void                     setStartDate(Date startDate);

    /** @return Date    Завершение платежа или null */
    public Date                     getFinishDate();
    public void                     setFinishDate(Date finishDate);

    /** @return IFieldView  Основное поле */
    public IField                   getTarget();

    /** @return Integer Статус платежа, значения в IPayment */
    public Integer                  getStatus();
    public void                     setStatus(Integer status);

    public boolean                  isPreparedForCancelling();
    public void                     delay(int interval);
    public void                     errorDelay();
    public boolean                  isDelayed();
    public void                     setDelayed(boolean delayed);

    public void                     setActive(boolean active);
    public boolean                  getActive();

    public void                     setSent(boolean sent);
    public boolean                  getSent();

    public void                     setDateOfProcess(Date dateOfProcess);
    public Date                     getDateOfProcess();

    public void                     incTryCount();
    public Integer                  getTryCount();
    public void                     setTryCount(Integer count);

    public void                     incErrorRepeatCount();
    public int                      getErrorRepeatCount();

    public String                   getStatusName();
    public String                   getPsTitle();
    public void                     setPsTitle(String title);
    public void                     setPsid(Integer psid);
    public void                     setFullValue(Double fullValue);
    public void                     setFields(Collection<IField> fields);
    public IField                   getField(Integer id);
}
