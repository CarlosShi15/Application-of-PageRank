clear all;clc
P_matrix = [0.41526176 0.15179316 0.22867514 0.30330525 0.26203807;  
             0.20763088 0.15179316 0.57168784 0.30330525 0.52407615;  
             0.0         0.15179316 0.45735027 0.0         0.26203807;  
             0.0         0.15179316 0.11433757 0.37913156 0.52407615;  
             0.10381544 0.07589658 0.11433757 0.07582631 0.26203807];
mc = dtmc(P_matrix,"StateNames", ["near library and lecture building" "near dorm" "out of school" "take away food" "other"]);
figure;
h = graphplot(mc, 'ColorEdges', true, 'ColorNodes',true);

% Iterate P_matrix
% Initialize P1 and P2  
P1 = P_matrix;  
P2 = P_matrix^2;  
  
% Initialize an array to record the differences  
diff_array = zeros(1, 1000); % Assume a maximum of 1000 iterations, adjust size as needed  
iter_count = 0; % Keep track of the iteration count  
  
% Loop to check convergence  
while max(P2(:) - P1(:)) > 1e-10  
    iter_count = iter_count + 1;  
    diff_array(iter_count) = max(P2(:) - P1(:)); % Record the current difference  
      
    % Update P1 and P2  
    P1 = P2;  
    P2 = P2 * P_matrix;  
      
    % Check if diff_array size is exceeded, if so, increase its size  
    if iter_count == length(diff_array)  
        diff_array = [diff_array, zeros(1, 1000)]; % Increase array size, here adding 1000 elements each time  
    end  
end  
  
% Plot the difference as a function of iteration count  
figure;  
plot(1:iter_count, diff_array(1:iter_count));  
title('Difference vs. Iteration Count');  
xlabel('Iteration Count');  
ylabel('Difference Value');  
grid on;  
  
% Display the final P2 matrix  
disp('Final P2 Matrix:');  
disp(P2);
