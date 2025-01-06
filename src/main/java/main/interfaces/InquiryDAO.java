package main.interfaces;

import main.models.Inquiry;
import java.util.List;

public interface InquiryDAO {
    void addInquiry(Inquiry inquiry);
    Inquiry getInquiryById(int id);
    List<Inquiry> getAllInquiries();
    void updateInquiry(Inquiry inquiry);
    void deleteInquiry(int id);
}
